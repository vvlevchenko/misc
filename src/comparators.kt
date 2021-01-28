

inline fun compareInt(a: Int, b: Int): Int = when {
    a > b -> 1
    a < b -> -1
    else -> 0
}

inline fun compareString (a:String, b: String): Int = a.compareTo(b)

interface Comparator1<T> {
     fun compare(a: T, b: T): Int
}

inline fun <reified T> comparator1(crossinline comparison: (a: T, b: T) -> Int): Comparator1<T> {
    return object: Comparator1<T> {
        override fun compare(a: T, b: T) = comparison(a, b)
    }
}

fun main () {
    val intComparator = comparator1(::compareInt)
    val stringComparator = comparator1(::compareString)

    val objectComparator = comparator1{ a: Any, b: Any -> when {
        a == b -> 0
        else -> 1
    }}
    println(intComparator.javaClass)
    if (intComparator === stringComparator) {
        println("they're equals")
    }
}