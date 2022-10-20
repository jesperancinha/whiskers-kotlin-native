import io.ktor.http.*
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
class Config(val port: Int)

@ExperimentalUnsignedTypes
fun main() {
    val configuration = runNativeDemos()
    val catSayingsService = CatSayingsService()
    val paragraphService = ParagraphService()
    makeACatsDay(catSayingsService)
    embeddedServer(CIO, port = configuration.port) {
        routing {
            install(ContentNegotiation) {
                json()
            }
            get("/") {
                call.respondText("Welcome to the Cat Ktor Service!")
            }
            post("/cat/saying") {
                println("Entering cat sayings!")
                val catSaying = call.receive<CatSaying>()
                println("Received cat saying ${catSaying.saying}")
                catSayingsService.save(catSaying)
                call.respondText("Cat comments stored correctly", status = HttpStatusCode.Created)
            }
            get("/cat/sayings") {
                call.respond(catSayingsService.getAll())
            }
            post("/story/paragraph") {
                println("Entering story paragraph!")
                val paragraph = call.receive<Paragraph>()
                println("Received cat saying ${paragraph.text}")
                paragraphService.save(paragraph)
                call.respondText("Story paragrah stored correctly", status = HttpStatusCode.Created)
            }
            get("/story/paragrahs") {
                call.respond(paragraphService.getAll())
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
    println(executeCommand("PGPASSWORD=red_cat psql -U whiskers -d whiskers -c 'SELECT 1' -h localhost"))
    return configuration
}

@ExperimentalUnsignedTypes
private fun makeACatsDay(catSayingsService: CatSayingsService) {
    println("--- A cat's day 🐈  ---")
    val driver = PostgresNativeDriver(
        host = "localhost",
        port = 5432,
        user = "whiskers",
        database = "whiskers",
        password = "red_cat"
    )
    val notPrepared = driver.executeQuery(null, "SELECT * from sayings.cat_lines limit 1;", parameters = 0, mapper = {
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