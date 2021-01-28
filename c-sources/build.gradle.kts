plugins {
    `native`
}

native {
    //environment{
    //    env("CFLAGS", "-g")
    //}
    suffixes {
        (".c" to ".bc") {
            tool("clang-mp-10")
            flags(*env("CFLAGS"), *env("CPPFLAGS"), "-c", "-emit-llvm", "-o", ruleOut(), ruleInFirst())
        }
        (".bc" to ".bc.o2") {
            tool("opt-mp-10")
            flags("-O2", "-o", ruleOut(), ruleInFirst())
        }
        (".bc.o2" to ".o") {
            tool("llc-mp-10")
            flags("--filetype=obj", "-o", ruleOut(), ruleInFirst())
        }
    }
    sourceSet {
        "main" {
            //file("")
            dir("src/main/c")
        }
    }

    val objSet = sourceSets["main"]!!.transform(".c" to ".bc")
        .transform(".bc" to ".bc.o2")
        .transform(".bc.o2" to ".o")
    target("hello", objSet) {
        tool("clang-mp-10")
        flags(*env("CFLAGS"), *env("LDFLAGS"), "-o", ruleOut(), *ruleInAll())
    }
}
