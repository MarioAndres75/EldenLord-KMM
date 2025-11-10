import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform) // 🔹 Necesario para usar compose.*
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.20"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.20"
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    // 🔹 Soporte completo para iOS
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                // ✅ Compose Multiplatform
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.components.resources)

                // ✅ Corrutinas y serialización
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

                // ✅ Ktor (networking multiplataforma)
                implementation("io.ktor:ktor-client-core:2.3.1")
                implementation("io.ktor:ktor-client-content-negotiation:2.3.1")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.1")
                // ✅ Kamel (para imágenes multiplataforma)

                implementation("media.kamel:kamel-image:0.9.5")

            }
        }

        val androidMain by getting {
            dependencies {
                // Cliente Ktor para Android
                implementation("io.ktor:ktor-client-okhttp:2.3.1")

                // Compose Android
                implementation("androidx.navigation:navigation-compose:2.8.3")
                implementation("androidx.compose.ui:ui:1.6.0")
                implementation("androidx.compose.material3:material3:1.2.0")
                implementation("androidx.activity:activity-compose:1.9.3")
            }
        }

        // 🔹 iOS Main unificado
        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
                // Cliente Ktor para iOS
                implementation("io.ktor:ktor-client-darwin:2.3.1")
            }
        }

        // Vinculamos los targets específicos de iOS con iosMain
        val iosX64Main by getting { dependsOn(iosMain) }
        val iosArm64Main by getting { dependsOn(iosMain) }
        val iosSimulatorArm64Main by getting { dependsOn(iosMain) }
    }
}

android {
    namespace = "org.example.project.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        testOptions.targetSdk = libs.versions.android.targetSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

compose {
    resources {
        publicResClass = true
        packageOfResClass = "org.example.project.shared.generated.resources"
    }
}
