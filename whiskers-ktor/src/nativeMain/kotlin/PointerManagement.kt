import kotlinx.cinterop.CPointer
import kotlinx.cinterop.toKString
import org.jesperancinha.native.*

internal fun CPointer<PGconn>.error(): String = PQerrorMessage(this)?.toKString().let {
    PQfinish(this)
    it ?: throw RuntimeException("Error message not created!")
}

internal fun CPointer<PGresult>.clear() = PQclear(this)

internal fun CPointer<PGconn>.exec(sql: String) = PQexec(this, sql)?.let {
    it.check(this)
    it.clear()
} ?: throw RuntimeException("Something went wrong with PQexec!")

internal fun CPointer<PGresult>.check(conn: CPointer<PGconn>): CPointer<PGresult> = PQresultStatus(this).run {
    check(this == PGRES_TUPLES_OK || this == PGRES_COMMAND_OK || this == PGRES_COPY_IN) {
        conn.error()
    }
}.let { this }
