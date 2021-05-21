package debugger.kt42208
fun box() {
    foo()()
}

fun box1() {
    val a = foo()
    a()
    a()
    a()
}

fun main() {
    box()
    box1()
}