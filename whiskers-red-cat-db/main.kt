import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.toKString
import redcat.*

fun main(args: Array<String>) {
    println("OOOOOOO")
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
    println("AAAAAA")
//    println(executeCommand("PGPASSWORD=red_cat psql -U whiskers -d whiskers -c 'SELECT 1' -h localhost"))
}