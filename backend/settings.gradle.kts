rootProject.name = "serverless-card-features-backend"
include("shared", "features:HelloWorld", "features:HelloWorldAdmin")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
