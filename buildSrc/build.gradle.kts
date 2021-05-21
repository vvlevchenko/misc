import org.jetbrains.kotlin.konan.parseCompilerVersion
import java.io.FileOutputStream
import java.io.ObjectOutputStream

buildscript {
    repositories {
        maven("https://cache-redirector.jetbrains.com/maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-dependencies")
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-util-io:1.4.21")
        classpath("org.jetbrains.kotlin:kotlin-build-gradle-plugin:0.0.24-minamoto-1")
    }
}

//import org.jetbrains.kotlin.konan.CompilerVersion.parseCompilerVersion

plugins {
    kotlin("jvm") version "1.4.21"
    `kotlin-dsl`
}

project.extra["version1"] = project.objects.property<org.jetbrains.kotlin.konan.CompilerVersion>().apply {
    set("1.4.0-M1-3245".parseCompilerVersion())
}

ObjectOutputStream(FileOutputStream("${project.buildDir}/version.ser")).use {
    it.writeObject("1.4.0-M1-3245".parseCompilerVersion())
}

repositories {
    maven("https://cache-redirector.jetbrains.com/maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-dependencies")
    mavenCentral()
}

dependencies{
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.21")
    implementation("org.jetbrains.kotlin:kotlin-util-io:1.4.21")
    implementation("org.jetbrains.kotlin:kotlin-build-gradle-plugin:0.0.24-minamoto-1")
}

val binaryVersion = true
//val generateVersion = tasks.register<org.jetbrains.VersionGenerator>("generateVersion") {
//    binaryVersion = true
//}
//
kotlin {
    sourceSets["main"].apply {
        kotlin.srcDir("src/main/kotlin")
        if (binaryVersion)
            kotlin.srcDir("src/binary-version/src/kotlin")
    }
}

tasks.named("compileKotlin") {
    //dependsOn(generateVersion)
}

gradlePlugin {
    plugins {
        create("c-to-obj") {
            id = "c-to-obj"
            implementationClass = "org.jetbrains.lang.LanguagePlugin"
        }

        create ("make") {
            id = "make"
            implementationClass = "org.github.vvlevchenko.gradle.make.MakePlugin"
        }
        
        create ("native") {
            id = "native"
            implementationClass = "org.jetbrains.gradle.plugins.native.tools.NativePlugin"
        }
    }
}
/*
val version1 = project.objects.property<org.jetbrains.kotlin.konan.CompilerVersion>().apply {
    org.jetbrains.kotlin.konan.CompilerVersion
    set(org.jetbrains.Version(1, 1, 2, 3))
}
 */