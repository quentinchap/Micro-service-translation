package com.quentinchap.traductionmachine.languages

import com.github.fakemongo.junit.FongoRule
import com.quentinchap.traductionmachine.translations.Translation
import com.quentinchap.traductionmachine.translations.TranslationRepository
import org.json.JSONObject
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*
import kotlin.collections.HashMap

@RunWith(SpringRunner::class)
@SpringBootTest
abstract class TranslationTestWithFongo {
    @get:Rule
    val fongoRule = FongoRule()

    @Autowired
    lateinit var translationRepository: TranslationRepository


    @Before
    fun setupTestDatabase() {
        translationRepository.save(TEST_TRANSLATION_1)

    }

    companion object {
        val lang = Lang(null, "test", "test", Locale("fr"))

        val tradHome: MutableMap<String, String> = mutableMapOf("WELCOME" to "bonjour")

        val trad: MutableMap<String, Any> = mutableMapOf("home" to tradHome)

        val TEST_TRANSLATION_1 = Translation(null, lang, trad)
    }
}