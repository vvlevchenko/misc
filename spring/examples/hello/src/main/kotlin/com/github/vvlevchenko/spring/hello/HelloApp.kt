package com.github.vvlevchenko.spring.hello

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@RestController
class HelloAppController {
    @RequestMapping("/", method = [RequestMethod.POST])
    fun index() = "Greeting from Kotlin Hello App"
}

@SpringBootApplication
open class HelloApp {

}

fun main(vararg args:String):Unit {
    runApplication<HelloApp>(*args) {

    }
}