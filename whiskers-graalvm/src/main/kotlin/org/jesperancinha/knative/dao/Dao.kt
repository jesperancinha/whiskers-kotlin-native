package org.jesperancinha.knative.dao

import org.jesperancinha.knative.dto.CatSayingDto
import org.jesperancinha.knative.dto.ParagraphDto
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

fun Paragraph.toDto() = ParagraphDto(
    text = this.text,
    id = this.id
)

fun CatSaying.toDto() = CatSayingDto(
    id = this.id,
    saying = this.saying
)

interface CatSayingRepository : CoroutineCrudRepository<CatSaying, Int>

interface ParagraphRepository : CoroutineCrudRepository<Paragraph, Int>

