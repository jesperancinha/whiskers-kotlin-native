import kotlinx.cinterop.*
import org.jesperancinha.knative.scramble_story
import org.jesperancinha.knative.tell_story

@kotlinx.cinterop.ExperimentalForeignApi
fun main(args: Array<String>) {
    val max = maxOf(1, if (args.isEmpty()) 0 else args[0].toInt())
    repeat(times = max) {
        scramble_story()
    }
    println(tell_story()?.toKString())
    println(scramble_story()?.toKString())
}
