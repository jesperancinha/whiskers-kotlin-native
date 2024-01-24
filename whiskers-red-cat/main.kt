import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.toKString
import redcat.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
fun main() {
    val story= redcat.tell_story() as CPointer<ByteVar>
    println("Welcome to the redcat story: ${story.toKString()}")
    println("The magic number is ${redcat.answer()}")
    println("Love ${redcat.love().toString()}")
}