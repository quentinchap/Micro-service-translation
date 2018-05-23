package com.quentinchap.traductionmachine.languages

import com.mongodb.MongoWriteException
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/langs")
class LangController @Autowired constructor(
        private val langService: LangService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new lang")
    fun create(@Valid @RequestBody @ApiParam(value = "Updated lang object", required = true) request: Lang, errors: Errors): Lang {
        if (errors.hasErrors())
            throw RuntimeException("Lang request is invalid")
        try {
            return langService.save(request.copy(id = null))
        } catch (e: MongoWriteException) {
            throw RuntimeException("Technical name should be unique and " + request.technicalName + " exist.")
        }
    }

    @GetMapping
    fun getAll(): List<Lang>{
        return langService.findAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): Optional<Lang> = langService.findById(id)


    @PutMapping("/{id}")
    fun updateById(
            @PathVariable id: String,
            @Valid @RequestBody @ApiParam(value = "Updated lang object", required = true) request: Lang,
            errors: Errors): Optional<Lang> = langService.updateById(id, request)


    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) {
        try {
            return langService.deleteById(id)
        } catch (e: IllegalArgumentException) {
            throw RuntimeException("Impossible to delete this object")
        }
    }
}