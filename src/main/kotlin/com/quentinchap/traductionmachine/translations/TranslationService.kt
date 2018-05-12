package com.quentinchap.traductionmachine.translations

import com.quentinchap.traductionmachine.languages.Lang
import com.quentinchap.traductionmachine.languages.LangService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TranslationService @Autowired constructor(
        private val translationRepository: TranslationRepository,
        private val langService: LangService

) {
    fun findAll(): List<Translation> {
        return translationRepository.findAll()
    }

    fun save(translation: Translation): Translation {
        try {
            return translationRepository.save(translation)
        } catch (e: Exception) {
            throw e
        }
    }

    fun updateById(id: String, newTranslation: TranslationRequest): Optional<Translation> {
        val translationToUpdate: Optional<Translation> = findById(id)

        if (translationToUpdate.isPresent) {
            var res = translationToUpdate.get().copy()

            // Update the lang of the translation if technical name exist
            if (newTranslation.lang != null) {
                val newLang: Optional<Lang> = langService.findByTechnicalName(newTranslation.lang.technicalName)
                if (newLang.isPresent) {
                    res.lang = newLang.get()
                }
            }

            if (newTranslation.override != null && newTranslation.override) {
                // Override translation. Erase all keys not present on the new translation object
                res.translations = newTranslation.translations
            } else {
                // Tree walk and update just keys present on the new translation object
                res.translations = updateTranslationTree(res.translations, newTranslation.translations)
            }

            return Optional.of(save(res))

        }
        return Optional.empty()
    }

    fun updateTranslationTree(oldTranslations: MutableMap<String, Any>, newTranslations: MutableMap<String, Any>): MutableMap<String, Any> {
        var res = oldTranslations

        for (newT in newTranslations) {
            if (oldTranslations.containsKey(newT.key)) {
                for (oldT in oldTranslations) {
                    if (newT.key == oldT.key) {
                        if (oldT.value is String) {
                            res[oldT.key] = newT.value
                        } else {
                            res[oldT.key] = updateTranslationTree(oldT.value as MutableMap<String, Any>, newT.value as MutableMap<String, Any>)
                        }
                    }
                }
            } else {
                res[newT.key] = newT.value
            }
        }

        return res
    }


    fun findById(id: String): Optional<Translation> {
        return translationRepository.findById(id)
    }

    fun deleteById(id: String) {
        return translationRepository.deleteById(id)
    }
}