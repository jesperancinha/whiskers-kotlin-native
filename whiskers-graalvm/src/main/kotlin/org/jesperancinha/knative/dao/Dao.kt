package org.jesperancinha.knative.dao

import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

@Table
data class Paragraph(
    val text: String,
    var id: Long? = null
)

@Table
data class CatSaying(
    var id: Long? = null,
    val saying: String
)

interface CatSayingRepository : CoroutineCrudRepository<CatSaying, Int>

interface ParagraphRepository : CoroutineCrudRepository<Paragraph, Int>

