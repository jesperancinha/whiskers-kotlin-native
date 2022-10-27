@file:OptIn(ExperimentalUnsignedTypes::class)

import io.ktor.util.*
import kotlin.text.toCharArray

const val alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

internal interface Service<T> {
    fun getAll(): List<T>
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
    fun getAllEncoded() = getAll().toCodeSayings()
}


@ExperimentalUnsignedTypes
internal class ParagraphService(driver: PostgresNativeDriver) : Service<Paragraph> {
    private val paragraphRepository = ParagraphRepository(nativeDriver = driver)
    override fun getAll() = paragraphRepository.findAll()
    override suspend fun getById(id: Long) = paragraphRepository.findById(id)
    override suspend fun save(entity: Paragraph) = paragraphRepository.save(entity)
    fun getAllEncoded() = getAll().toParagraphSaying()
}

private inline fun  List<Paragraph>.toParagraphSaying() =  map {
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
    Paragraph(id = it.id, text = codedParagraph)
}
inline fun List<CatSaying>.toCodeSayings() =
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
        CatSaying(id = it.id, saying = codedSaying)
    }
