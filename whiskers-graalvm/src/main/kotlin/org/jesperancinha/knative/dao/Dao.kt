package org.jesperancinha.knative.dao

import org.jesperancinha.knative.dto.CatSayingDto
import org.jesperancinha.knative.dto.ParagraphDto
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

@Table(schema = "story")
data class Paragraph(
    val text: String,
    var id: Long? = null
)

@Table(schema = "sayings")
data class CatLine(
    var id: Long? = null,
    val saying: String
)

fun Paragraph.toDto() = ParagraphDto(
    text = this.text,
    id = this.id
)

fun CatLine.toDto() = CatSayingDto(
    id = this.id,
    saying = this.saying
)

interface CatSayingRepository : CoroutineCrudRepository<CatLine, Int>

interface ParagraphRepository : CoroutineCrudRepository<Paragraph, Int>
