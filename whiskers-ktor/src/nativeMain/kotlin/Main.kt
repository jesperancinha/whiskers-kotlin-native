import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.cinterop.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.jesperancinha.native.tell_story
import platform.posix.*

@Serializable
data class Config(val server: Server, val database: Database)

@Serializable
data class Server(val port: Int)

@Serializable
data class Database(val port: Int, val host: String)

@ExperimentalUnsignedTypes
fun main() {
    val configuration = runNativeDemos()
    val driver = PostgresNativeDriver(
        host = configuration.database.host,
        port = configuration.database.port,
        user = "whiskers",
        database = "whiskers",
        password = "red_cat"
    )

    val catSayingsService = CatSayingsService(driver)
    val paragraphService = ParagraphService(driver)
    makeACatsDay(catSayingsService, configuration)
    embeddedServer(CIO, port = configuration.server.port) {
        routing {
            install(ContentNegotiation) {
                json()
            }
            get("/cat/sayings") {
                call.respondText("Welcome to the Cat Ktor Service!")
            }
            post("/cat/saying") {
                val catSaying = call.receive<CatSaying>()
                catSayingsService.save(catSaying).let { responseBody ->
                    call.respond(status = Created, responseBody)
                }
            }
            get("/cat/sayings") {
                call.respond(catSayingsService.getAll())
            }
            get("/cat/sayings/encoded") {
                call.respond(catSayingsService.getAllEncoded())
            }
            post("/story/paragraph") {
                val paragraph = call.receive<Paragraph>()
                paragraphService.save(paragraph).let { responseBody ->
                    call.respond(status = Created, responseBody)
                }
            }
            post("/story/paragraph/encoded") {
                val paragraphs = call.receive<List<Paragraph>>()
                paragraphs.toEncodedParagraphs().let { responseBody ->
                    call.respond(status = Created, responseBody)
                }
            }
            get("/story/paragraphs") {
                call.respond(paragraphService.getAll())
            }
            get("/story/paragraphs/encoded") {
                call.respond(paragraphService.getAllEncoded())
            }
        }
    }.start(wait = true)
}

private fun runNativeDemos(): Config {
    val story = tell_story() as CPointer<ByteVar>
    println("Welcome to the redcat story (server): ${story.toKString()}")
    val string = readText("application.json")
    println(string)
    val configuration = Json.decodeFromString<Config>(string)
    println(configuration)
    println(executeCommand("PGPASSWORD=red_cat psql -U whiskers -d whiskers -c 'SELECT 1' -h ${configuration.database.host}"))
    return configuration
}

@ExperimentalUnsignedTypes
private fun makeACatsDay(catSayingsService: CatSayingsService, configuration: Config) {
    println("--- A cat's day 🐈  ---")
    val driver = PostgresNativeDriver(
        host = configuration.database.host,
        port = configuration.database.port,
        user = "whiskers",
        database = "whiskers",
        password = "red_cat"
    )
    val notPrepared = driver.executeQuery(null, "SELECT * from sayings.cat_line limit 1;", parameters = 0, mapper = {
        it.next()
        it.getString(1)
    })
    catSayingsService.getAll().map { println(it.saying) }
    println(notPrepared.value)
    println("--- Cat logs out ---")
}

fun readText(filePath: String): String {
    val returnBuffer = StringBuilder()
    val file = fopen(filePath, "r") ?: throw IllegalArgumentException("Cannot open input file $filePath")
    try {
        memScoped {
            val readBufferLength = 64 * 1024
            val buffer = allocArray<ByteVar>(readBufferLength)
            var line = fgets(buffer, readBufferLength, file)?.toKString()
            while (line != null) {
                returnBuffer.append(line)
                line = fgets(buffer, readBufferLength, file)?.toKString()
            }
        }
    } finally {
        fclose(file)
    }

    val toString = returnBuffer.toString()
    printf(toString)
    return toString
}

fun executeCommand(command: String): String {
    val fp: CPointer<FILE>? = popen(command, "r")
    val buffer = ByteArray(4096)
    val returnString = StringBuilder()
    if (fp == NULL) {
        printf("Failed to run command")
        exit(1)
    }
    var scan = fgets(buffer.refTo(0), buffer.size, fp)
    if (scan != null) {
        while (scan != NULL) {
            returnString.append(scan!!.toKString())
            scan = fgets(buffer.refTo(0), buffer.size, fp)
        }
    }
    pclose(fp)
    return returnString.trim().toString()
}