package dwarf.test

import kotlinx.coroutines.*
import java.io.*
import java.util.concurrent.TimeUnit


fun executeDwarfUtil() {
    val process = ProcessBuilder("dwarfdump", "/Users/minamoto/ws/.git-trees/mpetrov/debug/program.kexe.dSYM/Contents/Resources/DWARF/program.kexe").start()

    val out = GlobalScope.async(Dispatchers.IO) {
        readStream(process, process.inputStream.buffered())
    }

    val err = GlobalScope.async(Dispatchers.IO) {
        readStream(process, process.errorStream.buffered())
    }

    runBlocking {
        val status = process.waitFor(5L, TimeUnit.MINUTES)

        println("status: ${process.exitValue()} out: ${out.await().length} err:${err.await().length}")

    }
}

private fun readStream(process: Process, stream: InputStream):String {
    var size = 4096
    val buffer = ByteArray(size) { 0 }
    val sunk = ByteArrayOutputStream()
    while (true) {
        size = stream.read(buffer, 0, buffer.size)
        if (size < 0 && !process.isAlive)
            break
        if (size > 0)
            sunk.write(buffer, 0, size)
    }
    return String(sunk.toByteArray())
}

fun main() {
    executeDwarfUtil()

}