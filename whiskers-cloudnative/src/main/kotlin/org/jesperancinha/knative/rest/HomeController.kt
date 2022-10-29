package org.jesperancinha.knative.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class HomeController {
    @GetMapping
    suspend fun getWelcomeMessage() = "Welcome to the Cat GraalVM Service!"
}