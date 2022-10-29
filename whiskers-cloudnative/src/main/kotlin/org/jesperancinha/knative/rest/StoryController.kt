package org.jesperancinha.knative.rest

import kotlinx.coroutines.flow.asFlow
import org.jesperancinha.knative.dto.ParagraphDto
import org.jesperancinha.knative.dto.StoryService
import org.jesperancinha.knative.dto.encodeParagraph
import org.jesperancinha.knative.dto.toEncodedParagraph
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/story")
class StoryController(
    val storyService: StoryService
) {

    @GetMapping("/paragraphs")
    fun getAllParagrahs() = storyService.getAllParagraphs()

    @GetMapping("/paragraphs/encoded")
    fun getAllEncodedParagraphs() = storyService.getCodedParagraphs()

    @GetMapping("/paragrahs/{id}")
    suspend fun getParagraphById(
        @PathVariable
        id: Int
    ) = storyService.getParagraphById(id)

    @PostMapping("/paragraph")
    suspend fun createNewParagraph(
        @RequestBody
        paragraphDto: ParagraphDto
    ) = storyService.saveParagraph(paragraphDto)

    @PostMapping("/paragraph/encoded")
    suspend fun createEncodedParagraph(
        @RequestBody
        paragraphDto: ParagraphDto
    ) = paragraphDto.encodeParagraph()

    @PostMapping("/paragraphs/encoded")
    fun createEncodedParagraphs(
        @RequestBody
        paragraphDtos: List<ParagraphDto>
    ) = paragraphDtos.asFlow().toEncodedParagraph()
}