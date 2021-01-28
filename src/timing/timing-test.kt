package timing

import kotlin.system.measureTimeMillis

fun <T> printMillisec(message: String, body: () -> T): T {
    var msec = 0L
    try {
        msec = measureTimeMillis {
            return body()
        }
    } finally {
        println("$message: $msec msec")
    }
    error("shouldn't happens")
}

class A

fun main() {
    println(printMillisec("Haha"){
       A()
    })
}