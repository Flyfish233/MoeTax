pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

plugins{
    id("de.fayard.refreshVersions") version "0.60.5"
}

rootProject.name = "MoeTax"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":composeApp")