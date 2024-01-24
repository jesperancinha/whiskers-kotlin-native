import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.toKString
import redcat.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
fun main() {
    println("--- A cat's day 🐈  ---")
    val conn = PQsetdbLogin(
        pghost = "localhost",
        pgport = "5432",
        pgtty = null,
        dbName = "whiskers",
        login = "whiskers",
        pwd = "red_cat",
        pgoptions =  null
    )
    println(PQstatus(conn))
    println("--- Cat logs out ---")
}