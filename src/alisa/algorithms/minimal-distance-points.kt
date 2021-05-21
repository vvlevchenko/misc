package alisa.algorithms

import kotlin.math.sqrt

val delta = 0.00001
class Point(val x:Double, val y:Double): Comparable<Point> {
    override operator fun compareTo (other: Point) = {y.compareTo(other.y)}
        .takeIf { kotlin.math.abs(x - other.x) < delta }?.invoke()
        ?: x.compareTo(other.x)
    override fun toString() = "($x, $y)"
}

fun Point.distanceTo(other: Point) = sqrt(square(x - other.x) + square(y - other.y))

private fun square(d: Double): Double = d * d

/**
 *
 */
//fun List<Point>.minimalDistance() {
//    if (size == 2)
//        return first().distanceTo(last())
//    val half = size / 2
//    val a1 = subList(0, half)
//    val a2 = subList(half + 1, size - 1)
//
//}

val patten = "\\(\\s*(.*)\\s*,\\s*(.*)\\s*\\)".toRegex()
fun String.toPoint():Point? = patten.matchEntire(this)?.destructured?.run {
        component1().toDoubleOrNull()?.run x@ {
            component2().toDoubleOrNull()?.run y@{
                Point(this@x, this@y)
            }
        }
    }



fun main() {
    println("(0,0.12123)".toPoint().toString())
    listOf("(0,0)", "(1,1)", "(2,2)", "(2,3)", "(3,3)").map {it.toPoint()!!}.sorted().joinToString(separator = ", ").also(::println)
}