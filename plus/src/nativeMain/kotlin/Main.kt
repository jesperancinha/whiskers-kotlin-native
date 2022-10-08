import io.ktor.util.*

fun main() {
    println("*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*")
    println(randomMessage())
    println("*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*")
    HashMap<String,String>()
}

fun randomMessage(): String {
   return listOf(
       "Good Morning!",
       "You are looking great today!",
       "What a great day today!",
       "Good job!",
       "I really appreciate what you just did! Thank you!",
       "Thanks for bringing me coffee!",
       "You are the best pal ever!",
       "I love working with you!",
       "Rise and shine!"
   ).random()
}
