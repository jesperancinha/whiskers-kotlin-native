import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.toKString
import redcat.*

fun main(args: Array<String>) {
    println("OOOOOOO")
    val conn = PQsetdbLogin(
        pghost = "host",
        pgport = "",
        pgtty = null,
        dbName = "database",
        login = "user",
        pwd = "",
        pgoptions = "options"
    )
}