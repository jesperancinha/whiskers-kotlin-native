package org.jesperancinha.knative.dto

import org.jesperancinha.knative.dao.CatSayingRepository
import org.jesperancinha.knative.dao.ParagraphRepository
import org.springframework.stereotype.Service

@Service
class CatService(
    val catSayingRepository: CatSayingRepository
) {
    fun getAllSayings() = catSayingRepository.findAll()
    suspend fun getSayingById(id: Int) = catSayingRepository.findById(id)
}

@Service
internal class StoryService(
    val paragraphRepository: ParagraphRepository
)
