plugins {
    id("lambda-conventions")
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    manifest {
        attributes(mapOf("Main-Class" to "com.example.HelloWorld"))
    }
}
