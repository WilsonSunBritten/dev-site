package com.britten.buildlogic

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File

data class FeatureConfig(
    val name: String,
    val description: String,
    val permission: String,
    val endpoint: String,
    val handler: String
)

fun generateSamTemplate(projectDir: File, buildDir: File) {
    val objectMapper = ObjectMapper(YAMLFactory()).registerKotlinModule()
    val template = objectMapper.readValue<MutableMap<String, Any>>(File(projectDir, "template.yaml"))
    val resources = template["Resources"] as MutableMap<String, Any>

    File(projectDir, "features").listFiles()?.forEach { featureDir ->
        val configFile = File(featureDir, "feature-config.json")
        if (configFile.exists()) {
            val config = objectMapper.readValue<FeatureConfig>(configFile)
            val lambdaResource = mutableMapOf<String, Any>(
                "Type" to "AWS::Serverless::Function",
                "Properties" to mutableMapOf(
                    "CodeUri" to "features/${featureDir.name}/build/libs/${featureDir.name}-all.jar",
                    "Handler" to config.handler,
                    "Runtime" to "java17",
                    "Events" to mapOf(
                        "ApiEvent" to mapOf(
                            "Type" to "Api",
                            "Properties" to mapOf(
                                "Path" to config.endpoint,
                                "Method" to "ANY",
                                "RestApiId" to mapOf("Ref" to "ApiGateway")
                            )
                        )
                    ),
                    "Environment" to mapOf(
                        "Variables" to mapOf(
                            "CARD_FEATURES_TABLE_NAME" to mapOf("Ref" to "CardFeaturesTable")
                        )
                    )
                )
            )
            resources["${featureDir.name}Function"] = lambdaResource
        }
    }

    objectMapper.writeValue(File(buildDir, "generated_template.yaml"), template)
}

fun main(args: Array<String>) {
    if (args.size != 2) {
        println("Usage: GenerateSamTemplate <projectDir> <buildDir>")
        return
    }
    generateSamTemplate(File(args[0]), File(args[1]))
}
