pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    plugins {
        kotlin("jvm") version "1.9.20"
        id("com.github.johnrengelman.shadow") version "8.1.1"
    }
}


rootProject.name = "serverless-card-features-backend"
include("shared", "features:HelloWorld", "features:HelloWorldAdmin")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
