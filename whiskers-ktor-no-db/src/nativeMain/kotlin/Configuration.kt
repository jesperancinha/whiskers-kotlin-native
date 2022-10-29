import kotlinx.serialization.Serializable

@Serializable
data class Config(val server: Server)

@Serializable
data class Server(val port: Int)
