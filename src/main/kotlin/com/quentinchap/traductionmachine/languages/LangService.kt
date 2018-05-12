package com.quentinchap.traductionmachine.languages

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class LangService @Autowired constructor(
        private val langRepository: LangRepository
) {
    fun findAll(): List<Lang> {
        return langRepository.findAll()
    }

    fun save(lang: Lang): Lang {
        try {
            return langRepository.save(lang)
        } catch (e: Exception) {
            throw e
        }
    }

    fun updateById(id:String, lang: Lang): Optional<Lang>
    {
        val langToUpdate: Optional<Lang> = findById(id)
        if(langToUpdate.isPresent)
        {
            return Optional.of(save(lang))
        }
        return Optional.empty()
    }

    fun findById(id: String): Optional<Lang> {
        return langRepository.findById(id)
    }

    fun findByTechnicalName(name:String): Optional<Lang> {
        return langRepository.findByTechnicalName(name)
    }

    fun deleteById(id: String) {
        return langRepository.deleteById(id)
    }
}