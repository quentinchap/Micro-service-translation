package com.quentinchap.traductionmachine.languages

import com.github.fakemongo.junit.FongoRule
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
abstract class LangTestWithFongo {
    @get:Rule
    val fongoRule = FongoRule()

    @Autowired
    lateinit var langRepository: LangRepository


    @Before
    fun setupTestDatabase() {
        langRepository.save(TEST_LANG_1)
        langRepository.save(TEST_LANG_2)
        langRepository.save(TEST_LANG_3)
        langRepository.save(TEST_LANG_4)
        langRepository.save(TEST_LANG_5)
    }

    companion object {
        val TEST_LANG_1 = Lang(null, "lang1", "lang1D", Locale("fr"))
        val TEST_LANG_2 = Lang(null, "lang2", "lang2D", Locale("fr"))
        val TEST_LANG_3 = Lang(null, "lang3", "lang3D", Locale("es"))
        val TEST_LANG_4 = Lang(null, "lang4", "lang4D", Locale("fr"))
        val TEST_LANG_5 = Lang(null, "lang5", "lang5D", Locale("de"))
    }
}