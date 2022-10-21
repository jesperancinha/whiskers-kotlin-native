package org.jesperancinha.knative.rest

import org.jesperancinha.knative.dto.ParagraphDto
import org.jesperancinha.knative.dto.StoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class StoryController(
    val storyService: StoryService
) {

    @GetMapping("/story/paragrahs")
    fun getAllParagrahs() = storyService.getAllParagraphs()

    @GetMapping("/story/paragrahs/{id}")
    suspend fun getParagraphById(
        @PathVariable
        id: Int) = storyService.getParagraphById(id)

    @PostMapping("/story/paragraph")
    suspend fun createNewParagraph(
        @RequestBody
        paragraphDto: ParagraphDto) = storyService.saveParagraph(paragraphDto)
}