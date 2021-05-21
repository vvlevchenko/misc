import org.jetbrains.kotlin.konan.CompilerVersion
import org.jetbrains.kotlin.konan.CompilerVersionImpl
import java.io.ObjectInputStream

val VERSION_PATH = "/version.ser"

fun main() {
    val version = ObjectInputStream(CompilerVersionImpl::class.java.getResourceAsStream(VERSION_PATH)).use {
        it.readObject() as CompilerVersion
    }
    println(version.toString())
}