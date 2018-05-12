package com.quentinchap.traductionmachine.languages

import com.fasterxml.jackson.annotation.JsonCreator
import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import javax.validation.constraints.NotBlank

@Document(collection = "languages")
data class Lang(
        @Id val id: String?,

        @Indexed(unique = true)
        @get:NotNull
        @get:NotBlank
        val technicalName: String,

        @get:NotNull
        @get:NotBlank
        val displayName: String,

        @get:NotNull
        val locale: Locale

)

data class LangRequest constructor(
        val technicalName: String,
        val displayName: String?,
        val locale: Locale?
)