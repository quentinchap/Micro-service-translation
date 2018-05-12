package com.quentinchap.traductionmachine.languages

import com.quentinchap.traductionmachine.translations.TranslationService
import org.junit.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals

class TranslationServiceTest : TranslationTestWithFongo() {
    @Autowired
    lateinit var translationService: TranslationService


    @Test
    fun findAllTests() {
        logger.info("Begin findAll Translation Tests")

         val translations = translationService.findAll()
         assertEquals(1, translations.size, "There should be 1 translations.")
        logger.info("End findAllTests")
    }


    companion object {
        val logger: Logger =
                LoggerFactory.getLogger(LangServiceTest::class.java)
    }
}