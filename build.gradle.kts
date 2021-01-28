plugins {
    kotlin("jvm") version "1.4.21"
}

kotlin {
    sourceSets["main"].apply {
        kotlin.srcDir("src")
        //java.srcDir("src")
    }
}

repositories {
    mavenCentral()
}

dependencies{
    implementation("org.jetbrains.kotlin:kotlin-test:1.4.21")
    implementation("org.antlr:antlr4:4.9")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("com.google.code.gson:gson:2.8.6")
}