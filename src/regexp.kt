fun main() {

    val msg0 = "Developer mode is currently disabled."
    val msg1 = "Developer mode is currently enabled."

    val r = Regex("^.*\\ (enabled|disabled).$")
    println(r.find(msg1)?.destructured?.component1())
}