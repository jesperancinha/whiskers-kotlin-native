import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.Accepted
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import platform.posix.fclose
import platform.posix.fgets
import platform.posix.fopen
import platform.posix.printf

fun main() {
    val configuration = getConfig()

    embeddedServer(CIO, port = configuration.server.port) {
        routing {
            install(ContentNegotiation) {
                json()
            }
            get {
                call.respondText("Welcome to the Cat Ktor Service!")
            }
            route("/cat") {
                route("/sayings") {
                    get {
                        call.respond(listOf<CatSaying>())
                    }
                    get("/encoded") {
                        call.respondWithEncodedFlow(status = OK, listOf<CatSaying>())
                    }
                }
                route("/saying") {
                    post {
                        val catSaying = call.receive<CatSaying>()
                        call.respond(status = Created, catSaying)
                    }
                }
            }
            route("/story") {
                route("/paragraph") {
                    post {
                        val paragraph = call.receive<Paragraph>()
                        call.respond(status = Created, paragraph)
                    }
                    post("/encoded") {
                        val paragraph = call.receive<Paragraph>()
                        call.respondWithEntity(status = Created, paragraph)
                    }
                }
                route("paragraphs") {
                    delete {
                       call.respond(status = Accepted,"")
                    }
                    post("/encoded") {
                        val paragraphs = call.receive<List<Paragraph>>()
                        call.respondWithEncodedFlow(status = Created, paragraphs)
                    }
                    get {
                        call.respond(listOf<Paragraph>())
                    }
                    get("/encoded") {
                        call.respond(listOf<Paragraph>())
                    }
                }
            }
        }
    }.start(wait = true)
}

private fun getConfig(): Config =
    readText("application.json").let { config -> Json.decodeFromString(config) }


private suspend fun ApplicationCall.respondWithEncodedFlow(status: HttpStatusCode, flow: List<Paragraph>) {
    respond(status = status, flow.toEncodedParagraphs())
}

private suspend fun ApplicationCall.respondWithEncodedFlow(status: HttpStatusCode, flow: List<CatSaying>) {
    respond(status = status, flow.toEncodedSayings())
}

private suspend fun ApplicationCall.respondWithEntity(status: HttpStatusCode, paragraph: Paragraph) {
    respond(status = status, paragraph.encodeParagraph())
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