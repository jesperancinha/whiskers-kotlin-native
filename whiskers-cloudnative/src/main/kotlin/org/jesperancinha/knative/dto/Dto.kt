package org.jesperancinha.knative.dto

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.jesperancinha.knative.dao.*
import org.springframework.stereotype.Service

const val alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

data class ParagraphDto(
    val text: String,
    var id: Long? = null
)

data class CatSayingDto(
    var id: Long? = null,
    val saying: String
)

val ParagraphDto.toData
    get() = Paragraph(
        id = this.id,
        text = this.text
    )


val CatSayingDto.toData
    get() = CatLine(
        id = this.id,
        saying = this.saying
    )


@Service
class CatService(
    val catSayingRepository: CatSayingRepository
) {
    fun getAllSayings() = catSayingRepository.findAll().map { it.toDto() }
    suspend fun getSayingById(id: Int) = catSayingRepository.findById(id)?.toDto()
    suspend fun saveSaying(sayingDto: CatSayingDto) = catSayingRepository.save(sayingDto.toData).toDto()
    fun getCodedSayings() = getAllSayings().toCodedSayings()
    suspend fun removeAll() {
        catSayingRepository.deleteAll()
    }
}

@Service
class StoryService(
    val paragraphRepository: ParagraphRepository
) {
    fun getAllParagraphs() = paragraphRepository.findAll().map { it.toDto() }
    suspend fun getParagraphById(id: Int) = paragraphRepository.findById(id)?.toDto()
    suspend fun saveParagraph(paragraphDto: ParagraphDto) = paragraphRepository.save(paragraphDto.toData).toDto()
    fun getCodedParagraphs() = getAllParagraphs().toEncodedParagraph()
   suspend fun removeAll() {
       paragraphRepository.deleteAll()
    }
}

fun Flow<ParagraphDto>.toEncodedParagraph() = map {
    it.encodeParagraph()
}

fun ParagraphDto.encodeParagraph(): ParagraphDto {
    val codedParagraph = text.split(" ").joinToString(" ") { word ->
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
    return ParagraphDto(id = id, text = codedParagraph)
}

fun Flow<CatSayingDto>.toCodedSayings() =
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
        CatSayingDto(id = it.id, saying = codedSaying)
    }
