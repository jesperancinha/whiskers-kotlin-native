package org.jesperancinha.knative

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WhiskersCloudNativeApplication

fun main(args: Array<String>) {
	runApplication<WhiskersCloudNativeApplication>(*args)
}
