package org.jesperancinha.knative

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WhiskersGraalvmApplication

fun main(args: Array<String>) {
	println("http://localhost:8080/cat/testdrives")
	println("http://localhost:8080/cat/sayings")
	println("http://localhost:8080/cat/sayings")
	println("http://localhost:8080/story/paragraphs")
	println("http://localhost:8080/story/paragraphs/encoded")
	runApplication<WhiskersGraalvmApplication>(*args)
}
