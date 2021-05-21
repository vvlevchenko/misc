package org.jetbrains.kotlin.konan
import java.io.*

val VERSION_PATH = "/META-INF/kotlin-native.compiler.version"
val CompilerVersion.Companion.CURRENT: CompilerVersion
    get() {
        return ObjectInputStream(CompilerVersionImpl::class.java.getResourceAsStream(VERSION_PATH)).use {
            it.readObject() as CompilerVersion
        }
    }