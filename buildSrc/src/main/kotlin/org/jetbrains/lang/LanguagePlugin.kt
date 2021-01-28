package org.jetbrains.lang

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

open class LanguagePlugin:Plugin<Project> {
    override fun apply(project: Project) {
        project.extensions.create("obj", ObjSetExtension::class.java, project)
        project.extensions.create("preprocessor", CppSetExtension::class.java, project)
    }

}

open class CppSetExtension(val project: Project) {

}

open class ObjSetExtension(val project: Project) {

    fun obj(name: String, configure: ObjSetExtension.()->Unit) {
        configure()
    }
}
