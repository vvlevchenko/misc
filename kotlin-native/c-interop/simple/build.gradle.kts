plugins {
    `native`
}

val konanHome = "/Users/minamoto/ws/.git-trees/minamoto/native-merge/dist"

project.findProperty("version1")!!.apply {
    println("VERSION: $this")
}

native {
    suffixes {
        (".kt" to "_api.h") {
            tool("${konanHome}/bin/konanc")
            flags("-p", "static",
            "-Xtemporary-files-dir=${project.buildDir}/.tmp_${ruleInFirst().removeSuffix(".kt")}",
            "-o", File(ruleInFirst().removeSuffix(".kt")).name, ruleInFirst())
        }
        (".c" to ".o") {
            tool("clang")
            flags("-c", "-o", ruleOut(), ruleInFirst())
        }
    }

    sourceSet {
        "app1-kt"{
            file("src/main/kotlin/app1.kt")
        }
        "app1-c" {
            file("src/main/c/app1.c")
        }
    }
    val objSetKt = sourceSets["app1-kt"]!!.transform(".kt" to "_api.h")
    val objSetC = sourceSets["app1-c"]!!.transform(".c" to ".o")/*.also {
        it.dependsOn(objSetKt)
    }*/
    target("app1", objSetC, objSetKt) {
        //it.dependsOn(objSetKt.implicitTasks())
        tool("clang++")
        flags("-o", ruleOut(), ruleInFirst(), "-L$projectDir", "-lapp1", "-lobjc", "-framework", "Foundation")
    }
}