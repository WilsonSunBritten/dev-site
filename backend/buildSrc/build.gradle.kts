plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation(kotlin("gradle-plugin"))
    implementation("com.github.johnrengelman.shadow:com.github.johnrengelman.shadow.gradle.plugin:8.1.1")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.13.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
}

gradlePlugin {
    plugins {
        create("samTemplate") {
            id = "com.britten.buildlogic.sam-template"
            implementationClass = "com.britten.buildlogic.SamTemplatePlugin"
        }
    }
}

kotlin {
    jvmToolchain(21)
}
