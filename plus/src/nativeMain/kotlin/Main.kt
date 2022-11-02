fun main() {
    println("*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*")
    println(randomMessage())
    println("*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*")
    val groupBy: Map<String, List<Int>> = allMessages().groupBy({ it }, { it.length })
    val hashMap = allMessages().groupBy({ it }, { it.length }).entries.fold(HashMap<String, List<Int>>()) { a, b ->
            a[b.key] = b.value
            a
        }
    println(groupBy::class.qualifiedName)
    println(hashMap::class.qualifiedName)
    val toTypedArray: Map<String, String> = allMessages().toTypedArray().associateBy { it }
    println(toTypedArray::class.qualifiedName)
}

fun randomMessage(): String {
    return allMessages().random()
}

private fun allMessages() = listOf(
    "Good Morning!",
    "You are looking great today!",
    "What a great day today!",
    "Good job!",
    "I really appreciate what you just did! Thank you!",
    "Thanks for bringing me coffee!",
    "You are the best pal ever!",
    "I love working with you!",
    "Rise and shine!"
)
