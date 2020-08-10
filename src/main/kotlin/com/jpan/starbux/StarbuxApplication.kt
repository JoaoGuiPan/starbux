package com.jpan.starbux

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class StarbuxApplication

fun main(args: Array<String>) {
	runApplication<StarbuxApplication>(*args)
}
