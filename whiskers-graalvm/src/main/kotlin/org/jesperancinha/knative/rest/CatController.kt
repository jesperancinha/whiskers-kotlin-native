package org.jesperancinha.knative.rest

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.jesperancinha.knative.dto.CatSayingDto
import org.jesperancinha.knative.dto.CatService
import org.jesperancinha.knative.dto.ParagraphDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cat")
class CatController(
    val catService: CatService
) {
    @GetMapping("/sayings")
    fun getCatSayings() = catService.getAllSayings()

    @GetMapping("/sayings/encoded")
    fun getCatEncodedSayings() = catService.getCodedSayings()

    @GetMapping("/saying/{id}")
    suspend fun getCatSayings(
        @NotNull
        @Size(min = 2, max = 14)
        @PathVariable id:Int
    ) = catService.getSayingById(id)

    @PostMapping("/saying")
    suspend fun createNewParagraph(
        @RequestBody
        sayingDto: CatSayingDto
    ) = catService.saveSaying(sayingDto)

    @DeleteMapping
    suspend fun deleteAllSayings() = catService.removeAll()
}
