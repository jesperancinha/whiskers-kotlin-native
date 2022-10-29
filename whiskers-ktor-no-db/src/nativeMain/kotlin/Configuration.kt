import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class Config(val server: Server, val database: Database)

@Serializable
data class Server(val port: Int)

@Serializable
data class Database(val port: Int, val host: String)
