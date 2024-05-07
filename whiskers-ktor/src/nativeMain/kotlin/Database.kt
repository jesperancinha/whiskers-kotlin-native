import PostgresNativeDriver.Companion.BINARY_RESULT_FORMAT
import PostgresNativeDriver.Companion.TEXT_RESULT_FORMAT
import app.cash.sqldelight.Query
import app.cash.sqldelight.Transacter
import app.cash.sqldelight.db.*
import kotlinx.cinterop.*
import kotlinx.datetime.*
import org.jesperancinha.native.*
import org.jesperancinha.native.ConnStatusType.CONNECTION_OK

/**
 * Based on the original file from https://github.com/hfhbd
 * https://github.com/hfhbd/postgres-native-sqldelight under Apache 2 Commons License
 * From 2022/10/11
 */
@ExperimentalUnsignedTypes
@kotlinx.cinterop.ExperimentalForeignApi
open class PostgresNativeDriver(
    host: String, database: String, user: String, password: String, port: Int = 5432, options: String? = null
) : SqlDriver {
    private var transaction: Transacter.Transaction? = null
    val conn = PQsetdbLogin(
        pghost = host,
        pgport = port.toString(),
        pgtty = null,
        dbName = database,
        login = user,
        pwd = password,
        pgoptions = options
    ) ?: throw NullPointerException("Unable to create connection!")

    init {
        require(PQstatus(conn) == CONNECTION_OK) {
            conn.error()
        }
    }

    override fun currentTransaction(): Transacter.Transaction? = transaction
    fun executeInsert(
        identifier: Int?,
        sql: String,
        parameters: Int = 0,
        binders: (SqlPreparedStatement.() -> Unit)? = null
    ) = execute(identifier, sql, parameters, binders)

    override fun execute(
        identifier: Int?,
        sql: String,
        parameters: Int,
        binders: (SqlPreparedStatement.() -> Unit)?
    ): QueryResult.Value<Long> {
        val preparedStatement = if (parameters != 0) PostgresPreparedStatement(parameters).apply {
            if (binders != null) {
                binders()
            }
        } else null
        val result = if (identifier != null) {
            if (!preparedStatementExists(identifier)) {
                prepareStatement(identifier, sql = sql, parameters = parameters, preparedStatement = preparedStatement)
                    ?.run { check(conn) } ?: throw RuntimeException("Unable to prepare PQprepare")
            } else {
                memScoped {
                    PQexecPrepared(
                        conn,
                        stmtName = identifier.toString(),
                        nParams = parameters,
                        paramValues = preparedStatement?.values(this),
                        paramFormats = preparedStatement?.formats?.refTo(0),
                        paramLengths = preparedStatement?.lengths?.refTo(0),
                        resultFormat = BINARY_RESULT_FORMAT
                    )
                }
            }
        } else {
            memScoped {
                PQexecParams(
                    conn,
                    command = sql,
                    nParams = parameters,
                    paramValues = preparedStatement?.values(this),
                    paramFormats = preparedStatement?.formats?.refTo(0),
                    paramLengths = preparedStatement?.lengths?.refTo(0),
                    resultFormat = BINARY_RESULT_FORMAT,
                    paramTypes = preparedStatement?.types?.refTo(0)
                )
            }
        }?.run { check(conn) } ?: throw RuntimeException("Unable to prepare PQprepare")
        val rows = PQcmdTuples(result)?.toKString() ?: ""
        result.clear()
        return QueryResult.Value(value = rows.toLongOrNull() ?: 0)
    }

    private fun preparedStatementExists(identifier: Int): Boolean {
        val result =
            executeQuery(null, "SELECT name FROM pg_prepared_statements WHERE name = $1", parameters = 1, binders = {
                bindString(0, identifier.toString())
            }, mapper = {
                it.next()
               QueryResult.Value(it.getString(0))
            })
        return result.value != null
    }

    fun <R> executeSelect(
        identifier: Int? = null,
        sql: String,
        mapper: (SqlCursor) -> QueryResult<R>,
        parameters: Int = 0,
        binders: (SqlPreparedStatement.() -> Unit)? = null
    ) = executeQuery(identifier, sql, mapper, parameters, binders)

    override fun <R> executeQuery(
        identifier: Int?,
        sql: String,
        mapper: (SqlCursor) -> QueryResult<R>,
        parameters: Int,
        binders: (SqlPreparedStatement.() -> Unit)?
    ): QueryResult<R> {
        val cursorName = if (identifier == null) "myCursor" else "cursor$identifier"
        val cursor = "DECLARE $cursorName CURSOR FOR"
        val preparedStatement = if (parameters != 0) {
            PostgresPreparedStatement(parameters).apply {
                if (binders != null) {
                    binders()
                }
            }
        } else null
        val result = if (identifier != null) {
            if (!preparedStatementExists(identifier)) {
                prepareStatement(identifier, cursor, sql, parameters, preparedStatement)
                    ?.run { check(conn).clear() } ?: throw RuntimeException("Unable to prepare PQprepare")
            }
            conn.exec("BEGIN")
            memScoped {
                PQexecPrepared(
                    conn,
                    stmtName = identifier.toString(),
                    nParams = parameters,
                    paramValues = preparedStatement?.values(this),
                    paramLengths = preparedStatement?.lengths?.refTo(0),
                    paramFormats = preparedStatement?.formats?.refTo(0),
                    resultFormat = BINARY_RESULT_FORMAT
                )
            }
        } else {
            conn.exec("BEGIN")
            memScoped {
                PQexecParams(
                    conn,
                    command = "$cursor $sql",
                    nParams = parameters,
                    paramValues = preparedStatement?.values(this),
                    paramLengths = preparedStatement?.lengths?.refTo(0),
                    paramFormats = preparedStatement?.formats?.refTo(0),
                    paramTypes = preparedStatement?.types?.refTo(0),
                    resultFormat = BINARY_RESULT_FORMAT
                )
            }
        }?.run { check(conn) } ?: throw RuntimeException("Unable to prepare PQprepare")

        val value = PostgresCursor(result, cursorName, conn).use(mapper)
        return value
    }


    private fun prepareStatement(
        identifier: Int,
        cursor: String? = null,
        sql: String,
        parameters: Int,
        preparedStatement: PostgresPreparedStatement?
    ) = PQprepare(
        conn,
        stmtName = identifier.toString(),
        query = if (cursor == null) sql else "$cursor $sql",
        nParams = parameters,
        paramTypes = preparedStatement?.types?.refTo(0)
    )

    internal companion object {
        const val TEXT_RESULT_FORMAT = 0
        const val BINARY_RESULT_FORMAT = 1
    }

    override fun addListener(vararg queryKeys: String, listener: Query.Listener) {
    }

    override fun close() {
        PQfinish(conn)
    }

    @kotlinx.cinterop.ExperimentalForeignApi
    override fun newTransaction(): QueryResult.Value<Transacter.Transaction> =
        conn.exec("BEGIN").let { QueryResult.Value(Transaction(transaction)) }

    override fun notifyListeners(vararg queryKeys: String) {
    }

    override fun removeListener(vararg queryKeys: String, listener: Query.Listener) {
    }

    private inner class Transaction(
        override val enclosingTransaction: Transacter.Transaction?
    ) : Transacter.Transaction() {

        override fun endTransaction(successful: Boolean): QueryResult<Unit> {
            if (enclosingTransaction == null) {
                if (successful) conn.exec("END") else conn.exec("ROLLBACK")
            }
            transaction = enclosingTransaction
            return QueryResult.Unit
        }
    }
}

@kotlinx.cinterop.ExperimentalForeignApi
class PostgresCursor(
    private var result: CPointer<PGresult>,
    private val name: String,
    private val conn: CPointer<PGconn>
) : SqlCursor, Closeable {
    override fun close() {
        result.clear()
        conn.exec("CLOSE $name")
        conn.exec("END")
    }

    override fun getBoolean(index: Int): Boolean? = getString(index)?.toBoolean()

    override fun getBytes(index: Int): ByteArray? {
        val isNull = PQgetisnull(result, tup_num = 0, field_num = index) == 1
        return if (isNull) null else {
            val bytes = PQgetvalue(result, tup_num = 0, field_num = index)
            val length = PQgetlength(result, tup_num = 0, field_num = index)
            bytes?.run { fromHex(length) } ?: throw RuntimeException("Unable to retrieve bytes from the database")
        }
    }

    private inline fun Int.fromHex(): Int = if (this in 48..57) {
        this - 48
    } else {
        this - 87
    }

    private fun CPointer<ByteVar>.fromHex(length: Int): ByteArray {
        val array = ByteArray((length - 2) / 2)
        for ((index, i) in (2 until length step 2).withIndex()) {
            val first = this[i].toInt().fromHex()
            val second = this[i + 1].toInt().fromHex()
            val octet = first.shl(4).or(second)
            array[index] = octet.toByte()
        }
        return array
    }

    override fun getDouble(index: Int): Double? = getString(index)?.toDouble()
    override fun getLong(index: Int): Long? = getString(index)?.toLong()
    override fun getString(index: Int): String? {
        val isNull = PQgetisnull(result, tup_num = 0, field_num = index) == 1
        return if (isNull) {
            null
        } else {
            val value = PQgetvalue(result, tup_num = 0, field_num = index)
            value?.toKString()
        }
    }

    override fun next(): QueryResult<Boolean> {
        result = PQexec(conn, "FETCH NEXT IN $name")?.run { check(conn) }
            ?: throw RuntimeException("Unable to prepare PQprepare")

        return QueryResult.Value(PQcmdTuples(result)?.let { it.toKString().toInt() == 1 }
            ?: throw RuntimeException("PQcmdTuples were not created!"))
    }
}

@OptIn(ExperimentalUnsignedTypes::class)
@kotlinx.cinterop.ExperimentalForeignApi
class PostgresPreparedStatement(private val parameters: Int) : SqlPreparedStatement {
    internal fun values(scope: AutofreeScope): CValuesRef<CPointerVar<ByteVar>> = createValues(parameters) {
        value = when (val value = _values[it]) {
            null -> null
            is ByteArray -> value.refTo(0).getPointer(scope)
            is String -> value.cstr.getPointer(scope)
            else -> null
        }
    }

    private val _values = arrayOfNulls<Any>(parameters)
    internal val lengths = IntArray(parameters)
    internal val formats = IntArray(parameters)
    internal val types = UIntArray(parameters)

    private fun bind(index: Int, value: String?, oid: UInt) {
        lengths[index] = if (value != null) {
            _values[index] = value
            value.length
        } else 0
        formats[index] = TEXT_RESULT_FORMAT
        types[index] = oid
    }

    override fun bindBoolean(index: Int, boolean: Boolean?) {
        bind(index, boolean?.toString(), 16u)
    }

    override fun bindBytes(index: Int, bytes: ByteArray?) {
        lengths[index] = if (bytes != null && bytes.isNotEmpty()) {
            _values[index] = bytes
            bytes.size
        } else 0
        formats[index] = BINARY_RESULT_FORMAT
        types[index] = 17u
    }

    override fun bindDouble(index: Int, double: Double?) {
        bind(index, double?.toString(), 701u)
    }

    override fun bindLong(index: Int, long: Long?) {
        bind(index, long?.toString(), 20u)
    }

    override fun bindString(index: Int, string: String?) {
        bind(index, string, 25u)
    }
}
