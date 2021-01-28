plugins {
    kotlin("jvm") version "1.4.21"
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies{
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.21")
}

kotlin {
    sourceSets["main"].apply {
        kotlin.srcDir("src/main/kotlin")
    }
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