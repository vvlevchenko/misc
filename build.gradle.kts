import org.jetbrains.kotlin.konan.CURRENT
import org.jetbrains.kotlin.konan.parseCompilerVersion
import java.io.*

buildscript {
    repositories {
        maven("https://cache-redirector.jetbrains.com/maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-dependencies")
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-build-gradle-plugin:0.0.24-minamoto-1")
    }
}

project.subprojects {
    project.extra["version1"] = project.objects.property<org.jetbrains.kotlin.konan.CompilerVersion>().apply {
        set("1.4.0-M1-3245".parseCompilerVersion())
    }
}


val generateVersion = tasks.register<org.jetbrains.VersionGenerator>("generateVersion") {
    binaryVersion = true
}

kotlin {
    sourceSets["main"].apply {
        kotlin.srcDir("src/main/kotlin")
    }
}

tasks.named("compileKotlin") {
    dependsOn(generateVersion)
}

plugins {
    kotlin("jvm") version "1.4.21"
}


val version1 = org.jetbrains.kotlin.konan.CompilerVersion.CURRENT
println("versionV: $version1")

//val version = File("${project.buildDir}/generated/version.ser").let { file ->
//    ObjectInputStream(FileInputStream(file)).use { stream ->
//        return@use stream.readObject() as org.jetbrains.kotlin.konan.CompilerVersion
//    }
//}
//
//println(version.toString())

kotlin {
    sourceSets["main"].apply {
        kotlin.srcDir("src")
        //java.srcDir("src")
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xir")
    }
}

repositories {
    mavenCentral()
}

dependencies{
    implementation("org.jetbrains.kotlin:kotlin-test:1.4.21")
    implementation("org.jetbrains.kotlin:kotlin-util-io:1.4.21")
    implementation("org.antlr:antlr4:4.9")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("com.google.code.gson:gson:2.8.6")
}