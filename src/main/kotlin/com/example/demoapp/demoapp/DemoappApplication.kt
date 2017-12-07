package com.example.demoapp.demoapp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class DemoappApplication

fun main(args: Array<String>) {
    SpringApplication.run(DemoappApplication::class.java, *args)
}

@RestController
@RequestMapping("hello")
class HelloController {
    @GetMapping("/string")
    fun helloString() = "Hello string!"

    @GetMapping("/service")
    fun helloService() = helloService.getHello()

    @GetMapping("/data") fun helloData() = Hello("Hello data!")
}

// Service
@Service
class HelloService {
    fun getHello() = "Hello service!"
}

@Autowired
lateinit var helloService: HelloService

// Data class
data class Hello(val message: String)
