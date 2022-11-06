package org.jesperancinha.knative.graalvm

class App {
    companion object {
        val story: String =
            "The red cat used to roam around in the neighbourhood. For some reason this cat found in Lucy a connection and became Lucy's friend"

        fun scrambleStory() = story.split(" ").joinToString(" ")
    }
}

fun main(args: Array<String>) {
    val max = maxOf(1, if (args.isEmpty()) 0 else args[0].toInt())
    repeat(times = max) {
        App.scrambleStory()
    }
    println(App.story)
    println(App.scrambleStory())
}
