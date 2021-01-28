import java.io.Serializable

private val versionPattern = "(\\d+)\\.(\\d+)(?:\\.(\\d+))?(?:-M(\\p{Digit}))?(?:-(\\p{Alpha}\\p{Alnum}*))?(?:-(\\d+))?".toRegex()

val versions = listOf("1.3.70", "1.4.0-M1-eap-25", "1.3.0", "1.3.0-M2")
val meta = listOf("RC2", "Rc2", "rC4", "rc1", "eap1", "eap2", "Eap3", "eAp4", "eaP5", "EAP3", "", "alpha", "beta")

fun main() {
    versions.forEach {
        println(it)
        val (major, minor, maintenance, milestone, metaString, build) = versionPattern.matchEntire(it)?.destructured ?: TODO()
        val milestoneVersionPattern = ".*(?:-M(\\d+))?.*".toRegex()
        val (milestone1) = milestoneVersionPattern.matchEntire(it)?.destructured ?: TODO()
        println("$it == ($major, $minor, $maintenance, $milestone ($milestone1), $metaString, $build)")

    }
    meta.forEach {
        println("$it == ${MetaVersion.findAppropriate(it)}")
    }
    val a = 42.apply {
        println(toString())
        this + 1
    }
    println("a: $a")

    val b = with(42){
        println(toString())
        this + 1
    }
    println("b: $b")

}

sealed class MetaVersion(val metaString: String) {
    companion object {
        fun findAppropriate(metaString: String): MetaVersion = when {
            DEV.metaString.equals(metaString, true) -> DEV
            ALPHA.metaString.equals(metaString, true) -> ALPHA
            BETA.metaString.equals(metaString, true) -> BETA
            RELEASE.metaString.equals(metaString, true) -> RELEASE
            EAP.eapPattern.matches(metaString) -> {
                val (numStr) = EAP.eapPattern.matchEntire(metaString)?.destructured
                        ?: error("Unknown meta version: $metaString")
                numStr.toIntOrNull()?.run { EAP(this) } ?: error("Unknown meta version: $metaString")
            }
            RC.rcPattern.matches(metaString) -> {
                val (numStr) = RC.rcPattern.matchEntire(metaString)?.destructured
                        ?: error("Unknown meta version: $metaString")
                numStr.toIntOrNull()?.run { RC(this) } ?: error("Unknown meta version: $metaString")
            }
            metaString.isBlank() -> RELEASE
            else -> error("Unknown meta version: $metaString")
        }
    }

    object DEV : MetaVersion("dev")
    class EAP(val number: Int) : MetaVersion("eap$number") {
        companion object {
            val eapPattern: Regex = "[eE][aA][pP](\\p{Digit})".toRegex()
        }
    }

    object ALPHA : MetaVersion("alpha")
    object BETA : MetaVersion("beta")
    class RC(val number: Int) : MetaVersion("rc$number") {
        companion object {
            val rcPattern: Regex = "[rR][cC](\\p{Digit})".toRegex()
        }
    }

    object RELEASE : MetaVersion("release")
}


interface CompilerVersion : Serializable {
    val meta: MetaVersion
    val major: Int
    val minor: Int
    val maintenance: Int
    val milestone: Int
    val build: Int

    fun toString(showMeta: Boolean, showBuild: Boolean): String

    companion object {
        // major.minor.patch-meta-build where patch, meta and build are optional.
        private val versionPattern = "(\\d+)\\.(\\d+)(?:\\.(\\d+))?(?:-M(\\p{Digit}))?(?:-(\\p{Alpha}\\p{Alnum}*))?(?:-(\\d+))?".toRegex()

        fun fromString(version: String): CompilerVersion {
            val (major, minor, maintenance, milestone, metaString, build) =
                    versionPattern.matchEntire(version)?.destructured
                            ?: throw IllegalArgumentException("Cannot parse Kotlin/Native version: $version")

            return CompilerVersionImpl(
                    MetaVersion.findAppropriate(metaString),
                    major.toInt(),
                    minor.toInt(),
                    maintenance.toIntOrNull() ?: 0,
                    milestone.toIntOrNull() ?: -1,
                    build.toIntOrNull() ?: -1
            )
        }
    }
}

fun String.parseCompilerVersion() = CompilerVersion.fromString(this)

data class CompilerVersionImpl(
        override val meta: MetaVersion = MetaVersion.DEV,
        override val major: Int,
        override val minor: Int,
        override val maintenance: Int,
        override val milestone: Int = 0,
        override val build: Int = -1
) : CompilerVersion {

    override fun toString(showMeta: Boolean, showBuild: Boolean) = buildString {
        append(major)
        append('.')
        append(minor)
        if (maintenance != 0) {
            append('.')
            append(maintenance)
        }
        if (milestone != -1) {
            append("-M")
            append(milestone)
        }
        if (showMeta) {
            append('-')
            append(meta.metaString)
        }
        if (showBuild && build != -1) {
            append('-')
            append(build)
        }
    }

    private val isRelease: Boolean
        get() = meta == MetaVersion.RELEASE

    private val versionString by lazy { toString(!isRelease, !isRelease) }

    override fun toString() = versionString
}

//"(\\d+)\\.(\\d+)(?:\\.(\\d+))?(?:-M(\\p{Digit}))?(?:-(\\p{Alpha}\\p{Alnum}*))?(?:-(\\d+))?"
class VersionBuilder{

}
