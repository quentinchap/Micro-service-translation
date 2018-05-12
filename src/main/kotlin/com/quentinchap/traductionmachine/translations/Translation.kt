package com.quentinchap.traductionmachine.translations

import com.fasterxml.jackson.annotation.JsonCreator
import com.quentinchap.traductionmachine.languages.Lang
import com.quentinchap.traductionmachine.languages.LangRequest
import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "translations")
data class Translation(
        @Id val id: String?,

        @Indexed(unique = true)
        @get:NotNull
        var lang: Lang,

        var translations: MutableMap<String, Any>

)

data class TranslationRequest constructor(
        val translations: MutableMap<String, Any>,
        val lang: LangRequest?,
        val override: Boolean?
)