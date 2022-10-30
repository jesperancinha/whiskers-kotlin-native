import kotlinx.cinterop.*
import org.jesperancinha.knative.tell_story

fun main() {
   print(tell_story()?.toKString())
}
