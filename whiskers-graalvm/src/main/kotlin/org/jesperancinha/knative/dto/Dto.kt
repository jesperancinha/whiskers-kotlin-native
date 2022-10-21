package org.jesperancinha.knative.dto

import kotlinx.coroutines.flow.map
import org.jesperancinha.knative.dao.CatSayingRepository
import org.jesperancinha.knative.dao.Paragraph
import org.jesperancinha.knative.dao.ParagraphRepository
import org.jesperancinha.knative.dao.toDto
import org.springframework.stereotype.Service


data class ParagraphDto(
    val text: String,
    var id: Long? = null
)

data class CatSayingDto(
    var id: Long? = null,
    val saying: String
)

val ParagraphDto.toDto
    get() = Paragraph(
        id = this.id,
        text = this.text
    )


@Service
class CatService(
    val catSayingRepository: CatSayingRepository
) {
    fun getAllSayings() = catSayingRepository.findAll().map { it.toDto() }
    suspend fun getSayingById(id: Int) = catSayingRepository.findById(id)?.toDto()
}

@Service
class StoryService(
    val paragraphRepository: ParagraphRepository
) {
    fun getAllParagraphs() = paragraphRepository.findAll().map { it.toDto() }

    suspend fun getParagraphById(id: Int) = paragraphRepository.findById(id)?.toDto()
    suspend fun saveParagraph(paragraphDto: ParagraphDto) = paragraphRepository.save(paragraphDto.toDto).toDto()
}
