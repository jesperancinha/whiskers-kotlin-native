@file:OptIn(ExperimentalUnsignedTypes::class)

import io.ktor.util.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.text.toCharArray

const val alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

inline fun List<Paragraph>.toEncodedParagraphs() = map { it.encodeParagraph() }

fun Paragraph.encodeParagraph(): Paragraph {
    val codedParagraph = text.split(" ").joinToString(" ") { word ->
        word.toCharArray().fold("") { acc, value ->
            "$acc${
                alphabet.indexOf(value).let { indexResult ->
                    when (indexResult) {
                        -1 -> value
                        else -> indexResult
                    }
                }.toString().padStart(2, '0')
            }"
        }
    }
    return Paragraph(id = id ?: -1, text = codedParagraph)
}

inline fun List<CatSaying>.toEncodedSayings() =
    map {
        val codedSaying = it.saying.split(" ").joinToString(" ") { word ->
            word.toCharArray().fold("") { acc, value ->
                "$acc${
                    alphabet.indexOf(value).let { indexResult ->
                        when (indexResult) {
                            -1 -> value
                            else -> indexResult
                        }
                    }.toString().padStart(2, '0')
                }"
            }
        }
        CatSaying(id = it.id ?: -1, saying = codedSaying)
    }
