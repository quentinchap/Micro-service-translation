package com.quentinchap.traductionmachine.languages

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LangRepository : MongoRepository<Lang, String>{
    fun findByTechnicalName(name: String): Optional<Lang>
}