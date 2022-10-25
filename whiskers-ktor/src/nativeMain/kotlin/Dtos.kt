@file:OptIn(ExperimentalUnsignedTypes::class)

internal interface Service<T> {
    fun getAll(): List<T>
    suspend fun getById(id: Long): T
    suspend fun save(entity: T): T
}

@ExperimentalUnsignedTypes
internal class CatSayingsService(driver: PostgresNativeDriver) : Service<CatSaying> {
    private val catSayingsRepository = CatSayingsRepository(nativeDriver = driver)
    override fun getAll() = catSayingsRepository.findAll()
    override suspend fun getById(id: Long) = catSayingsRepository.findById(id)
    override suspend fun save(entity: CatSaying) = catSayingsRepository.save(entity)
}

@ExperimentalUnsignedTypes
internal class ParagraphService(driver: PostgresNativeDriver) : Service<Paragraph> {
    private val paragraphRepository = ParagraphRepository(nativeDriver = driver)
    override fun getAll() = paragraphRepository.findAll()
    override suspend fun getById(id: Long) = paragraphRepository.findById(id)
    override suspend fun save(entity: Paragraph) = paragraphRepository.save(entity)
}