import java.io.BufferedReader
import java.io.StringReader

val output = """Matching Java Virtual Machines (6):
    11.0.5 (x86_64) "Oracle Corporation" - "Java SE 11.0.5" /Library/Java/JavaVirtualMachines/jdk-11.0.5.jdk/Contents/Home
    10.0.2 (x86_64) "Oracle Corporation" - "Java SE 10.0.2" /Library/Java/JavaVirtualMachines/jdk-10.0.2.jdk/Contents/Home
    9.0.4 (x86_64) "Oracle Corporation" - "Java SE 9.0.4" /Library/Java/JavaVirtualMachines/jdk-9.0.4.jdk/Contents/Home
    1.8.0_242 (x86_64) "Amazon" - "Amazon Corretto 8" /Library/Java/JavaVirtualMachines/amazon-corretto-8.jdk/Contents/Home
    1.8.0_231 (x86_64) "Oracle Corporation" - "Java SE 8" /Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home
    1.8.0_202 (x86_64) "Oracle Corporation" - "Java SE 8" /Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home
/Library/Java/JavaVirtualMachines/jdk-11.0.5.jdk/Contents/Home
"""

fun main() {
    listOf(1,2,3,4).joinToString().also { println(it) }
    val macOsJavaHomeOutRegexes = listOf(Regex("""\s+(\S+),\s+(\S+):\s+".*?"\s+(.+)"""),
            Regex("""\s+(\S+)\s+\((.*?)\):\s+(.+)"""),
                    Regex("""\s+(\S+)\s+\((.+)\)\s+".+"\s+-\s+".+"\s+(.+)"""))
    BufferedReader(StringReader(output)).lines().forEach {
        println("it: $it")
        for (rex in macOsJavaHomeOutRegexes) {
            val result = rex.matchEntire(it)
            if (result != null) {
                with(result) {
                    println("${groupValues.joinToString()}")
                }
            }
        }
    }
}