package com.britten.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

class SamTemplatePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register<GenerateSamTemplateTask>("generateSamTemplate") {
            group = "build"
            description = "Generates the SAM template including all feature functions"
        }
    }
}

abstract class GenerateSamTemplateTask : org.gradle.api.DefaultTask() {
    @org.gradle.api.tasks.TaskAction
    fun generate() {
        generateSamTemplate(project.projectDir, project.buildDir)
    }
}
