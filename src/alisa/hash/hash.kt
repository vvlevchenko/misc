package alisa.hash

import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import java.util.*

class HashTable(val nbucket:Int, val hash:(Int)->Int = ::identity) {
    private val bucket =  Array(nbucket){ mutableListOf<Pair<Int, Int>>()}

    private fun lookup(key:Int) = bucket[hash(key) % nbucket].find { it.first == key }

    fun add(key: Int, value:Int) {
        remove(key)
        bucket[hash(key) % nbucket].add(key to value)
    }

    fun remove(key:Int) {
        lookup(key)?.let { bucket[hash(key) % nbucket].remove(it) }
    }

    fun isValueExists(value:Int) = bucket.any{ it.any{ pair ->  pair.second == value}}

    operator fun get(key:Int):Int? = lookup(key)?.second
    operator fun set(key: Int, value: Int) = add(key, value)
}

fun identity(i:Int) = i

enum class Command(val value: String){
    ADD("add"),
    REMOVE("remove"),
    LOOKUP("lookup"),
    VALUE("value"),
    EXIT("exit")
}

const val promt = "> "

fun main(){
    val scan = Scanner(System.`in`)
    val table = HashTable(7)

    do {
        val cmd = scan.promtedCommand("command (${Command.values().joinToString { it.value }})")

        when(cmd){
            Command.ADD -> {
                val key = scan.promtedInt("key")
                val value = scan.promtedInt("value")
                table[key] = value
            }

            Command.REMOVE -> {
                print("key$promt")
                val key = scan.nextLine().toInt()
                table.remove(key)
            }

            Command.LOOKUP -> {
                val key = scan.promtedInt("key")
                println("answer:${table[key] ?: "no key found"}")
            }

            Command.VALUE -> {
                val value = scan.promtedInt("value")
                println("answer:${table.isValueExists(value)}")
            }

            Command.EXIT -> {}
        }
    } while (cmd != Command.EXIT)
}

private fun Scanner.promtedInt(msg: String): Int {
    try {
        print("$msg$promt")
        return nextLine().trim().toInt()
    } catch (e: NumberFormatException) {
        return promtedInt(msg)
    }
}

private fun Scanner.promtedCommand(msg:String):Command {
    try {
        print("$msg$promt")
        val querry = nextLine()
        return Command.valueOf(querry.trim().toUpperCase())
    }catch (e:IllegalArgumentException) {
        return promtedCommand(msg)
    }
}