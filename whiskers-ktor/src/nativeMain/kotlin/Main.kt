import app.cash.sqldelight.Query
import app.cash.sqldelight.Transacter
import app.cash.sqldelight.db.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.cinterop.*
import kotlinx.datetime.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.uuid.UUID
import kotlinx.uuid.toUUID
import org.jesperancinha.native.*
import platform.posix.*
import kotlin.time.Duration

@Serializable
class Config(val port: Int)

@ExperimentalUnsignedTypes
fun main(args: Array<String>) {
    val story = tell_story() as CPointer<ByteVar>
    println("Welcome to the redcat story (server): ${story.toKString()}")
    val string = readText("application.json")
    println(string)
    val configuration = Json.decodeFromString<Config>(string)
    println(configuration)
    println(executeCommand("PGPASSWORD=red_cat psql -U whiskers -d whiskers -c 'SELECT 1' -h localhost"))
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
    CatSayingsRepository(driver).findAll().map { println(it.saying) }
    println(notPrepared.value)
    println("--- Cat logs out ---")
    embeddedServer(CIO, port = configuration.port) {
        routing {
            get("/") {
                call.respondText("Hello, world!")
            }
        }
    }.start(wait = true)
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