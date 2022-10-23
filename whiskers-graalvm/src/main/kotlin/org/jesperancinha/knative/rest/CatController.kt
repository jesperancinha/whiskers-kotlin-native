package org.jesperancinha.knative.rest

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.jesperancinha.knative.dto.CatService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CatController(
    val catService: CatService
) {

    @GetMapping
    suspend fun getWelcomeMessage() = "Welcome to the Cat GraalVM Service!"

    @GetMapping("/cat/sayings")
    fun getCatSayings() = catService.getAllSayings()

    @GetMapping("/cat/saying/{id}")
    suspend fun getCatSayings(
        @NotNull
        @Size(min = 2, max = 14)
        @PathVariable id:Int
    ) = catService.getSayingById(id)
}