package com.quentinchap.traductionmachine.hello

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/v1/hello")
class HelloController {

    @GetMapping
    fun bonjour():String{
        return "coucou"
    }

    @GetMapping("es")
    fun hola():String{
        return "hola"
    }

}