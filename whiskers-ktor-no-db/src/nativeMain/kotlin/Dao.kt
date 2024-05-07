import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class CatSaying(
    var id: Long? = null,
    val saying: String
)

@Serializable
data class Paragraph(
    val text: String,
    var id: Long? = null
)
