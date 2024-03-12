pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    plugins {
        kotlin("jvm") version "1.9.20"
        kotlin("multiplatform") version("1.9.20")
        kotlin("plugin.serialization") version("1.9.20")
        id("org.jetbrains.compose") version("1.6.0")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "ktor-6777"

include(":desktop")
include(":http")
include(":webapp")
