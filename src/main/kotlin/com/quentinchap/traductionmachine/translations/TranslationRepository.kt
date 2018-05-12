package com.quentinchap.traductionmachine.translations

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TranslationRepository : MongoRepository<Translation, String>