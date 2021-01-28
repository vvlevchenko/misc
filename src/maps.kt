fun main() {
    mapOf(("one" to 1) to "one-1", ("two" to 2) to "two-2").keys.forEach {
        println(it.second)
    }
}