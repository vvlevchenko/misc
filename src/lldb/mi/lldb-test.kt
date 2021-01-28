package lldb.mi

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.nio.file.Path
import java.nio.file.Paths
import java.util.concurrent.TimeUnit

val lldbMi = "lldb"

data class ProcessOutput(
        val program: Path,
        val process: Process,
        val stdout: String,
        val stderr: String,
        val durationMs: Long
) {
    fun thrownIfFailed(): ProcessOutput {
        fun renderStdStream(name: String, text: String): String =
                if (text.isBlank()) "$name is empty" else "$name:\n$text"

        check(process.exitValue() == 0) {
            """$program exited with non-zero value: ${process.exitValue()}
              |${renderStdStream("stdout", stdout)}
              |${renderStdStream("stderr", stderr)}
            """.trimMargin()
        }
        return this
    }
}

private fun readStream(prefix: String, process: Process, stream: InputStream): String {
    var size = 4096
    val buffer = ByteArray(size) { 0 }
    val sunk = ByteArrayOutputStream()
    while (true) {
        size = stream.read(buffer, 0, buffer.size)
        if (size < 0 && !process.isAlive)
            break
        if (size > 0) {
            sunk.write(buffer, 0, size)
            //println("$prefix: ${String(sunk.toByteArray())}")
        }
    }
    return String(sunk.toByteArray())
}


fun subprocess(program: Path, vararg args: String): ProcessOutput {
    val start = System.currentTimeMillis()
    val process = ProcessBuilder(program.toString(), *args).start()
    val out = GlobalScope.async(Dispatchers.IO) {
        readStream("out", process, process.inputStream.buffered())
    }

    val err = GlobalScope.async(Dispatchers.IO) {
        readStream("err", process, process.errorStream.buffered())
    }



    return runBlocking {
        try {
            val status = process.waitFor(5L, TimeUnit.MINUTES)
            if (!status) {
                out.cancel()
                err.cancel()
                error("$program timeouted")
            }
        }catch (e:Exception) {
            out.cancel()
            err.cancel()
            error(e)
        }
        ProcessOutput(program, process, out.await(), err.await(), System.currentTimeMillis() - start)
    }
}

fun main() {
    val output = subprocess(Paths.get("lldb"),
            "/Users/minamoto/ws/.git-trees/minamoto/debugger/bindings/KT-41291/program.kexe",
            "-b",
            "-o", "command script import /Users/minamoto/ws/.git-trees/minamoto/debugger/bindings/KT-41291/llvmDebugInfoC/src/scripts/konan_lldb.py",
            "-o", "b test.kt:3",
            "-o", "r",
            "-o", "fr v",
            "-o", "bt",
            "-o", "q"
    ).thrownIfFailed()
    println(output.stdout)
    println(output.stderr)
}