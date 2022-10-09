package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WhiskersGraalvmApplication

fun main(args: Array<String>) {
	runApplication<WhiskersGraalvmApplication>(*args)
}
