package com.quentinchap.traductionmachine.hello

import org.junit.Test

class HelloTests {
    @Test
    fun testsHelloFr() {
        val objectToTest = HelloController()
        assert(objectToTest.bonjour() === "coucou")
    }

    @Test
    fun testsHelloEs() {
        val objectToTest = HelloController()
        assert(objectToTest.hola() === "hola")
    }
}