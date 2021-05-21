package lldb.mi

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.selects.select
import java.io.BufferedReader
import java.io.BufferedWriter

fun alwaysGood(x:String) = true
fun check2(x: String ):Boolean {
    println("response: $x")
    return true
}
val separatorCommand = "p (void)0"
val commandSeparatorResponse = "(lldb) $separatorCommand"




fun main() {
    val response = Channel<String>()
    val out = Channel<String>()
    val process = ProcessBuilder("lldb").start()
    GlobalScope.async(Dispatchers.IO) {
        println("out reader started")
        val reader = process.inputStream.bufferedReader()
        streamReader("out", out, reader)
    }

    val err = Channel<String>()
    GlobalScope.async(Dispatchers.IO) {
        println("err reader started")
        val reader = process.errorStream.bufferedReader()
        streamReader("err", err, reader)
    }


    val commands = listOf(
        "help" to ::alwaysGood,
        "version" to ::alwaysGood,
        "expr -- 1+1" to ::check2,
        "expr -- \$0 + 1" to ::alwaysGood
        )
    GlobalScope.async(Dispatchers.IO) {
        println("cmd channel started")
        val writer = process.outputStream.bufferedWriter()
        session(writer, out, err) {
            "help" {
                println(it.split("\r", "\n")[0])
            }
            "version" {

            }
            "expr -- 1+1" {

            }
            "expr -- \$0 + 1" {

            }
            "process attach -n program.kexe" {
                println(it)
            }

            "add-dsym /Users/minamoto/ws/.git-trees/minamoto/kn-build-fixes/program.kexe.dSYM" {
                println(it)
            }
            "b test.kt:5" {
                println(it)
            }

            /**
             * TODO: no way to get event here about breakpoint triggered or hitted :/
             */
            "c" {
                println(it)

            }

            "fr v" {
                println(it)
            }

            "detach"{
                println(it)
            }
            quit()
        }
    }


    runBlocking {


        val exitStatus = process.waitFor()
        println("exit: $exitStatus")
    }
}


class LldbSession(val writer: BufferedWriter, val out: Channel<String>, val err: Channel<String>) {
    suspend inline fun quit() = "quit"{}
    suspend operator fun String.invoke(body:(String)->Unit) {
        writer.appendLine(this)
        writer.flush()
        writer.appendLine(separatorCommand)
        writer.flush()
        select<Unit> {
            out.onReceive {
                body(it)
            }
            err.onReceive {
                println("err: $it")
            }
        }
    }
}
inline fun session(writer: BufferedWriter, out: Channel<String>, err: Channel<String>, body: LldbSession.() -> Unit) = LldbSession(writer, out, err).body()


private suspend fun streamReader(
    name: String,
    channel: Channel<String>,
    reader: BufferedReader
): Boolean {
    buildString {
        while(true) {
            val line = reader.readLine()
            if (commandSeparatorResponse == line) {
                channel.send(toString())
                clear()
            } else
                appendLine(line)
        }
    }
    return channel.close()
}