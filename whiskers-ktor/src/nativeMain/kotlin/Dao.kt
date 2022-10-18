@file:OptIn(ExperimentalUnsignedTypes::class)

internal interface Repository<T> {
    fun findAll(): List<T>
    fun findById(long: Long): T
    fun first(): T
    fun save(entity: T): T
}

data class CatSaying(
    var id: Long? = null,
    val saying: String
)

@ExperimentalUnsignedTypes
class CatSayingsRepository(
    val nativeDriver: PostgresNativeDriver
) : Repository<CatSaying> {
    override fun findAll(): List<CatSaying> =
        nativeDriver.executeQuery(null, "SELECT * from sayings.cat_lines;", parameters = 0, mapper = {
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
        }).value


    override fun findById(long: Long): CatSaying {
        TODO("Not yet implemented")
    }

    override fun first(): CatSaying {
        TODO("Not yet implemented")
    }

    override fun save(entity: CatSaying) =
        nativeDriver.execute(null, "INSERT INTO sayings.cat_lines VALUES (${entity.saying})", parameters = 0)
            .let { entity }

}