package org.jesperancinha.knative.rest

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

    @GetMapping("/cat/saying")
    fun getCatSayings() = catService.getAllSayings()

    @GetMapping("/cat/saying/{id}")
    suspend fun getCatSayings(@PathVariable id:Int) = catService.getSayingById(id)
}