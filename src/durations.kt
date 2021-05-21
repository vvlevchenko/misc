import java.time.Duration

fun main () {
    println(Duration.parse("PT3H").toMillis())
}