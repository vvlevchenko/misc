inline fun Int.using(f: (Int) -> Int):Int {
    return f(this)
}


fun main() {
    42.using { it ->
        if (it != 24) {
            return@using it.using {
                if (it == 42) {
                    return@using 43
                }
                return@using 52
            }
        }
        return@using 42
    }
}