package org.jesperancinha.knative.rest

import kotlinx.coroutines.flow.asFlow
import org.jesperancinha.knative.dto.ParagraphDto
import org.jesperancinha.knative.dto.StoryService
import org.jesperancinha.knative.dto.toEncodedParagraph
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class StoryController(
    val storyService: StoryService
) {

    @GetMapping("/story/paragraphs")
    fun getAllParagrahs() = storyService.getAllParagraphs()

    @GetMapping("/story/paragraphs/encoded")
    fun getAllEncodedParagraphs() = storyService.getCodedParagraphs()

    @GetMapping("/story/paragrahs/{id}")
    suspend fun getParagraphById(
        @PathVariable
        id: Int
    ) = storyService.getParagraphById(id)

    @PostMapping("/story/paragraph")
    suspend fun createNewParagraph(
        @RequestBody
        paragraphDto: ParagraphDto
    ) = storyService.saveParagraph(paragraphDto)

    @PostMapping("/story/paragraph/encoded")
    suspend fun createEncodedParagraph(
        @RequestBody
        paragraphDtos: List<ParagraphDto>
    ) = paragraphDtos.asFlow().toEncodedParagraph()
}