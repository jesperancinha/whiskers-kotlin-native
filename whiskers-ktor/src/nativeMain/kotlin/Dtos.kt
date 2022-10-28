@file:OptIn(ExperimentalUnsignedTypes::class)

import io.ktor.util.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.text.toCharArray

const val alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

internal interface Service<T> {
    fun getAll(): Flow<T>
    suspend fun getById(id: Long): T
    suspend fun save(entity: T): T
}

@ExperimentalUnsignedTypes
class CatSayingsService(
    driver: PostgresNativeDriver,
    val catSayingsRepository: CatSayingsRepository = CatSayingsRepository(nativeDriver = driver)
) : Service<CatSaying> {
    override fun getAll() = catSayingsRepository.findAll()
    override suspend fun getById(id: Long) = catSayingsRepository.findById(id)
    override suspend fun save(entity: CatSaying) = catSayingsRepository.save(entity)
    fun getAllEncoded() = getAll().toEncodedSayings()
}


@ExperimentalUnsignedTypes
internal class ParagraphService(driver: PostgresNativeDriver) : Service<Paragraph> {
    private val paragraphRepository = ParagraphRepository(nativeDriver = driver)
    override fun getAll() = paragraphRepository.findAll()
    override suspend fun getById(id: Long) = paragraphRepository.findById(id)
    override suspend fun save(entity: Paragraph) = paragraphRepository.save(entity)
    fun getAllEncoded() = getAll().toEncodedParagraphs()
}

inline fun Flow<Paragraph>.toEncodedParagraphs() = map {
    val codedParagraph = it.text.split(" ").joinToString(" ") { word ->
        word.toCharArray().fold("") { acc, value ->
            "$acc${
                alphabet.indexOf(value).let { indexResult ->
                    when (indexResult) {
                        -1 -> value
                        else -> indexResult
                    }
                }.toString().padStart(2, '0')
            }"
        }
    }
    Paragraph(id = it.id ?: -1, text = codedParagraph)
}

inline fun Flow<CatSaying>.toEncodedSayings() =
    map {
        val codedSaying = it.saying.split(" ").joinToString(" ") { word ->
            word.toCharArray().fold("") { acc, value ->
                "$acc${
                    alphabet.indexOf(value).let { indexResult ->
                        when (indexResult) {
                            -1 -> value
                            else -> indexResult
                        }
                    }.toString().padStart(2, '0')
                }"
            }
        }
        CatSaying(id = it.id ?: -1, saying = codedSaying)
    }
