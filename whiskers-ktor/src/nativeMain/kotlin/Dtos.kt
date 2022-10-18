@file:OptIn(ExperimentalUnsignedTypes::class)

@ExperimentalUnsignedTypes
internal class CatSayingsService {

    val catSayingsRepository = CatSayingsRepository(
        PostgresNativeDriver(
            host = "localhost",
            port = 5432,
            user = "whiskers",
            database = "whiskers",
            password = "red_cat"
        )
    )

    fun getAllCatSayings() = catSayingsRepository.findAll()

    suspend fun getCatSayingsById(id: Long) = catSayingsRepository.findById(id)

   suspend fun saveCatSayings(catSaying: CatSaying): CatSaying = catSayingsRepository.save(catSaying)
}