class App {
    companion object{
        val story: String = "The red cat used to roam around in the neighbourhood. For some reason this cat found in Lucy a connection and became Lucy's friend"

        fun scrambleStory() = story.split(" ").joinToString(" ")
    }
}

fun main() {
    println(App.story)
    println(App.scrambleStory())
}
