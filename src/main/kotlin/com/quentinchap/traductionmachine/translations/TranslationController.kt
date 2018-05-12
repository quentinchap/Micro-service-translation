package com.quentinchap.traductionmachine.translations

import com.fasterxml.jackson.databind.util.JSONPObject
import com.mongodb.MongoWriteException
import com.quentinchap.traductionmachine.languages.Lang
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/translations")
class TranslationController @Autowired constructor(
        private val translationService: TranslationService
) {

    @GetMapping
    fun findAll(): List<Translation> {
        return translationService.findAll()
    }

    @PostMapping
    fun create(@Valid @RequestBody @ApiParam(value = "Created translation object", required = true) request: Translation, errors: Errors): Translation {
        if (errors.hasErrors())
            throw RuntimeException("Lang request is invalid")
        try {
            return translationService.save(request.copy(id = null))
        } catch (e: MongoWriteException) {
            throw RuntimeException("There is already some translation for " + request.lang.technicalName)
        }
    }

    @PutMapping("/{id}")
    fun updateById(
            @PathVariable id: String,
            @RequestBody @ApiParam(value = "Updated translation object", required = true) request: TranslationRequest,
            errors: Errors): Optional<Translation> {
        return translationService.updateById(id, request)
    }

}