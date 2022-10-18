@file:OptIn(ExperimentalUnsignedTypes::class)

val driver = PostgresNativeDriver(
    host = "localhost",
    port = 5432,
    user = "whiskers",
    database = "whiskers",
    password = "red_cat"
)

internal interface Service<T> {
    fun getAll(): List<T>
    suspend fun getById(id: Long): T
    suspend fun save(entity: T): T
}

@ExperimentalUnsignedTypes
internal class CatSayingsService : Service<CatSaying> {
    private val catSayingsRepository = CatSayingsRepository(driver)
    override fun getAll() = catSayingsRepository.findAll()
    override suspend fun getById(id: Long) = catSayingsRepository.findById(id)
    override suspend fun save(entity: CatSaying) = catSayingsRepository.save(entity)
}

@ExperimentalUnsignedTypes
internal class ParagraphService : Service<Paragraph> {
    private val paragraphRepository = ParagraphRepository(driver)
    override fun getAll() = paragraphRepository.findAll()
    override suspend fun getById(id: Long) = paragraphRepository.findById(id)
    override suspend fun save(entity: Paragraph) = paragraphRepository.save(entity)
}