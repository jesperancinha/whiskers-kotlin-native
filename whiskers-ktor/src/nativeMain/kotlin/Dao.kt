@file:OptIn(ExperimentalUnsignedTypes::class)

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.serialization.Serializable

internal interface Repository<T> {
    fun findAll(): List<T>
    suspend fun findById(id: Long): T
    suspend fun first(): T
    suspend fun save(entity: T): T
    suspend fun deleteAll(): Unit
    val singleEntityMapper: (SqlCursor) -> QueryResult<T>
    val listEntityMapper: (SqlCursor) -> QueryResult<List<T>>
}

@Serializable
 data class CatSaying(
    var id: Long? = null,
    val saying: String
)

@Serializable
data class Paragraph(
    val text: String,
    var id: Long? = null
)

@ExperimentalUnsignedTypes
@kotlinx.cinterop.ExperimentalForeignApi
open class CatSayingsRepository(
    val nativeDriver: PostgresNativeDriver,
    override val singleEntityMapper: (SqlCursor) -> QueryResult<CatSaying> = {
        it.next()
        QueryResult.Value(CatSaying(
            id = it.getLong(0) ?: -1,
            saying = it.getString(1) ?: throw RuntimeException("Element found without a text!")
        ))
    },
    override val listEntityMapper: (SqlCursor) ->  QueryResult<List<CatSaying>> = {
        val all = mutableListOf<CatSaying>()
        while (it.next().value) {
            all.add(
                CatSaying(
                    id = it.getLong(0) ?: -1,
                    saying = it.getString(1) ?: throw RuntimeException("Element found without a text!")
                )
            )
        }
        QueryResult.Value(all.toList())
    }

) : Repository<CatSaying> {

    @kotlinx.cinterop.ExperimentalForeignApi
    override fun findAll(): List<CatSaying> =
        nativeDriver.executeSelect(
            sql = "SELECT * from sayings.cat_line ;",
            mapper = listEntityMapper
        ).value

    @kotlinx.cinterop.ExperimentalForeignApi
    override suspend fun findById(id: Long): CatSaying = nativeDriver.executeSelect(
        sql = "SELECT * from sayings.cat_line  limit 1 where id = ${id};",
        mapper = singleEntityMapper
    ).value

    @kotlinx.cinterop.ExperimentalForeignApi
    override suspend fun first(): CatSaying =
        nativeDriver.executeSelect(
            sql = "SELECT * from sayings.cat_line  limit 1;",
            mapper = singleEntityMapper
        ).value

    @kotlinx.cinterop.ExperimentalForeignApi
    override suspend fun deleteAll() {
        nativeDriver.execute(null, "DELETE FROM sayings.cat_line",0)
    }

    @kotlinx.cinterop.ExperimentalForeignApi
    override suspend fun save(entity: CatSaying) =
        nativeDriver.executeInsert(null, "INSERT INTO sayings.cat_line (saying)VALUES ('${entity.saying}')")
            .let { entity }

}

@ExperimentalUnsignedTypes
@kotlinx.cinterop.ExperimentalForeignApi
class ParagraphRepository(
    val nativeDriver: PostgresNativeDriver,
    @kotlinx.cinterop.ExperimentalForeignApi
    override val singleEntityMapper: (SqlCursor) ->         QueryResult<Paragraph> = {
        it.next()
        QueryResult.Value(Paragraph(
            id = it.getLong(0) ?: -1,
            text = it.getString(1) ?: throw RuntimeException("Element found without a text!")
        ))
    },
    @kotlinx.cinterop.ExperimentalForeignApi
    override val listEntityMapper: (SqlCursor) ->  QueryResult.Value<List<Paragraph>> = {
        val all = mutableListOf<Paragraph>()
        while (it.next().value) {
            all.add(
                Paragraph(
                    id = it.getLong(0) ?: -1,
                    text = it.getString(1) ?: throw RuntimeException("Element found without a text!")
                )
            )
        }
        QueryResult.Value(all.toList())
    }
) : Repository<Paragraph> {
    @kotlinx.cinterop.ExperimentalForeignApi
    override fun findAll(): List<Paragraph> = nativeDriver.executeSelect(
            sql = "SELECT * from story.paragraph;",
            mapper = listEntityMapper
        ).value

    @kotlinx.cinterop.ExperimentalForeignApi
    override suspend fun findById(id: Long): Paragraph =nativeDriver.executeSelect(
        sql = "SELECT * from story.paragraph limit 1 where id = ${id};",
        mapper = singleEntityMapper
    ).value

    @kotlinx.cinterop.ExperimentalForeignApi
    override suspend fun first(): Paragraph  =
        nativeDriver.executeSelect(
            sql = "SELECT * from story.paragraph limit 1;",
            mapper = singleEntityMapper
        ).value

    @kotlinx.cinterop.ExperimentalForeignApi
    override suspend fun deleteAll() {
        nativeDriver.execute(null, "DELETE FROM story.paragraph",0)
    }

    @kotlinx.cinterop.ExperimentalForeignApi
    override suspend fun save(entity: Paragraph): Paragraph =
        nativeDriver.executeInsert(null, "INSERT INTO story.paragraph(text)VALUES ('${entity.text.escapePostgreSQL()}')")
            .let { entity }
}

fun String.escapePostgreSQL(): String = this.replace(Regex("([^'])'([^'])"),"$1''$2")
