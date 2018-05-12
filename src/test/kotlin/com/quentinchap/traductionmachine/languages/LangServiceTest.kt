package com.quentinchap.traductionmachine.languages

import org.junit.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals

class LangServiceTest : LangTestWithFongo() {
    @Autowired
    lateinit var langService: LangService


    @Test
    fun findAllTests() {
        logger.info("Begin findAllTests")

         val langs = langService.findAll()
         assertEquals(5, langs.size, "There should be 5 langs.")

        logger.info("End findAllTests")
    }


    companion object {
        val logger: Logger =
                LoggerFactory.getLogger(LangServiceTest::class.java)
    }
}