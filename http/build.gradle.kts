plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
    mavenLocal()
}

kotlin {
    applyDefaultHierarchyTemplate()
    jvm()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            isStatic = true
        }
    }
    js(IR) {
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.insert-koin:koin-core:3.5.3")

                implementation(libs.kotlin.serialization)
                implementation(libs.kotlinx.datetime)
                implementation(libs.ktor.content.negotiation)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.core)
                implementation(libs.ktor.kotlinx.serialization)

                implementation(libs.ktor.client.json)
                implementation(libs.ktor.client.serialization)
            }
        }
        val commonTest by getting
        val jvmMain by getting {
            dependencies {
                implementation(libs.ktor.http)
                implementation(libs.ktor.client.logging.jvm)
            }
        }
        val jvmTest by getting

        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
        val iosTest by getting

        val jsMain by getting {
            dependencies {
                implementation(libs.ktor.client.js)
            }
        }
    }
}