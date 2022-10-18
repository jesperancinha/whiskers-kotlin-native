@file:OptIn(ExperimentalUnsignedTypes::class)

import app.cash.sqldelight.db.SqlCursor
import kotlinx.serialization.Serializable

internal interface Repository<T> {
    fun findAll(): List<T>
    suspend fun findById(id: Long): T
    suspend fun first(): T
    suspend fun save(entity: T): T
}

@Serializable
data class CatSaying(
    var id: Long? = null,
    val saying: String
)

@ExperimentalUnsignedTypes
class CatSayingsRepository(
    val nativeDriver: PostgresNativeDriver
) : Repository<CatSaying> {

    private val singleEntityMapper: (SqlCursor) -> CatSaying = {
        it.next()
        CatSaying(
            id = it.getLong(0) ?: -1,
            saying = it.getString(1) ?: throw RuntimeException("Element found without a text!")
        )
    }

    private val listEntityMapper: (SqlCursor) -> List<CatSaying> = {
        val all = mutableListOf<CatSaying>()
        while (it.next()) {
            all.add(
                CatSaying(
                    id = it.getLong(0) ?: -1,
                    saying = it.getString(1) ?: throw RuntimeException("Element found without a text!")
                )
            )
        }
        all.toList()
    }

    override fun findAll(): List<CatSaying> =
        nativeDriver.executeSelect(
            sql = "SELECT * from sayings.cat_lines;",
            mapper = listEntityMapper
        ).value

    override suspend fun findById(id: Long): CatSaying = nativeDriver.executeSelect(
        sql = "SELECT * from sayings.cat_lines limit 1 where id = ${id};",
        mapper = singleEntityMapper
    ).value

    override suspend fun first(): CatSaying =
        nativeDriver.executeSelect(
            sql = "SELECT * from sayings.cat_lines limit 1;",
            mapper = singleEntityMapper
        ).value

    override suspend fun save(entity: CatSaying) =
        nativeDriver.executeInsert(null, "INSERT INTO sayings.cat_lines(saying)VALUES ('${entity.saying}')")
            .let { entity }

}