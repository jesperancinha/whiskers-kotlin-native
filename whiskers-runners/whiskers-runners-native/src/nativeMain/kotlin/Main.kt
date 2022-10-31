import kotlinx.cinterop.*
import org.jesperancinha.knative.scramble_story
import org.jesperancinha.knative.tell_story

fun main() {
   println(tell_story()?.toKString())
   println(scramble_story()?.toKString())
}
