import java.io.File

fun File.dependencies():List<String> {
    val libraryRegexp = Regex("""^import\s+platform\.(\S+)\..*$""")
    return this.readLines().filter(libraryRegexp::containsMatchIn).map { libraryRegexp.matchEntire(it)?.groups?.get(1)?.value ?: "" }.toSortedSet().toList()
}
val validPropertiesNames = listOf("kotlin.native.home", "org.jetbrains.kotlin.native.home", "org.jetbrains.kotlin.native.home")
val location = validPropertiesNames.mapNotNull { System.getProperty(it) }


val output1 = """
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by com.intellij.util.ReflectionUtil (file:/Users/minamoto/ws/.git-trees/minamoto/native-merge/dist1/konan/lib/kotlin-native.jar) to method java.util.ResourceBundle.setParent(java.util.ResourceBundle)
WARNING: Please consider reporting this to the maintainers of com.intellij.util.ReflectionUtil
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
warning: skipping /Users/minamoto/ws/.git-trees/minamoto/native-merge/kotlin-native/backend.native/tests/build/1.2/empty.klib. The library versions don't match. Expected '5.6', found '1.2'
"""
fun main() {
    File("/Users/minamoto/ws/.git-trees/minamoto/native-merge/kotlin-native/backend.native/tests/compilerChecks/t15.kt").dependencies().forEach(::println)
    println(location)

    println(Regex("""WARNING:.*""").replace(output1, "").trim())


}
