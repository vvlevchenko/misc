package org.jetbrains

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.*
import org.gradle.api.tasks.Optional
import java.util.*
import java.util.regex.Pattern
import org.jetbrains.kotlin.konan.*
import java.io.*


fun Project.kotlinNativeProperties() = Properties().apply {
    this["konanVersion"] = "1.5.20"
    this["konanMetaVersion"] = "eap"
    this["binaryKotlinNativeVersionFormat"] = true
}

open class VersionGenerator: DefaultTask() {
    private val kotlinNativeProperties = project.kotlinNativeProperties()

    @Input
    var binaryVersion = project.kotlinNativeProperties()["binaryKotlinNativeVersionFormat"]?.toString()?.toBoolean() ?: false

    @OutputDirectory
    val versionSourceDirectory = project.file("build/generated")

    @OutputFile
    open var versionFile: File? = project.file("${versionSourceDirectory.path}/org/jetbrains/kotlin/konan/CompilerVersionGenerated.kt")

    @Input
    open val konanVersion =  kotlinNativeProperties["konanVersion"].toString()


    // TeamCity passes all configuration parameters into a build script as project properties.
    // Thus we can use them here instead of environment variables.
    @Optional
    @Input
    open val buildNumber = project.findProperty("build.number")?.toString()

    @Input
    open val meta = kotlinNativeProperties["konanMetaVersion"]?.let{ MetaVersion.valueOf(it.toString().toUpperCase()) } ?: MetaVersion.DEV

    private val versionPattern = Pattern.compile(
            "^(\\d+)\\.(\\d+)(?:\\.(\\d+))?(?:-M(\\p{Digit}))?(?:-(\\p{Alpha}\\p{Alnum}*))?(?:-(\\d+))?$"
    )

    @TaskAction
    open fun generateVersion() {
        val matcher = versionPattern.matcher(konanVersion)
        require(matcher.matches()) { "Cannot parse Kotlin/Native version: \$konanVersion" }
        val major = matcher.group(1).toInt()
        val minor = matcher.group(2).toInt()
        val maintenanceStr = matcher.group(3)
        val maintenance = maintenanceStr?.toInt() ?: 0
        val milestoneStr = matcher.group(4)
        val milestone = milestoneStr?.toInt() ?: -1
        project.logger.info("BUILD_NUMBER: $buildNumber")
        var build = -1
        if (buildNumber != null) {
            val buildNumberSplit = buildNumber!!.split("-".toRegex()).toTypedArray()
            build = buildNumberSplit[buildNumberSplit.size - 1].toInt() // //7-dev-buildcount
        }

        val versionObject = CompilerVersionImpl(meta, major, minor, maintenance, milestone, build)
        versionObject.serialize()


    }

    private fun CompilerVersion.serialize() {
        versionFile = if (binaryVersion)
            project.file("${project.buildDir}/classes/kotlin/main/META-INF/kotlin-native.compiler.version")
        else
            project.file("${versionSourceDirectory.path}/org/jetbrains/kotlin/konan/CompilerVersionGenerated.kt")
        versionFile!!.parentFile.mkdirs()
        if (!binaryVersion) {
            PrintWriter(versionFile).use { printWriter ->
                printWriter.println(
                        """|package org.jetbrains.kotlin.konan
                       |internal val currentCompilerVersion: CompilerVersion =
                       |CompilerVersionImpl($meta, $major, $minor,
                       |                    $maintenance, $milestone, $build)
                       |val CompilerVersion.Companion.CURRENT: CompilerVersion
                       |get() = currentCompilerVersion""".trimMargin()
                )
            }
        } else {
            ObjectOutputStream(FileOutputStream(versionFile)).use {
                it.writeObject(this)
            }
        }
    }
}

