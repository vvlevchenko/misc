package com.github.vvlevchenko.spring.hello

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import org.springframework.web.client.postForObject


@Configuration
open class HelloConfiguration {
    @Bean
    open fun restTemplate(builder: RestTemplateBuilder):RestTemplate =
        builder.rootUri("http://localhost:8080").build()
}

@RestController
class HelloAppUserController constructor(private val restTemplate: RestTemplate) {
    @RequestMapping("/")
    fun index() = buildString {
        appendLine("Hello from HelloAppUserController")
        appendLine(restTemplate.postForObject<String>("/"))
    }
}

@SpringBootApplication
open class HelloAppUser

fun main(vararg args:String):Unit {
    runApplication<HelloAppUser>(*args) {
        setDefaultProperties(mapOf("server.port" to "9090"))
    }
}