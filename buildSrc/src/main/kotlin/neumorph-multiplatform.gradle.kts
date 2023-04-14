@file:Suppress("UnstableApiUsage")
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    android()
    ios()
//    iosSimulatorArm64()
    jvm("desktop")

    kotlin {
        sourceSets {
            val commonTest by getting
            val commonMain by getting {
                commonTest.dependsOn(this)
                dependencies {

                }
            }
            val androidUnitTest by getting
            val androidMain by getting {
                androidUnitTest.dependsOn(this)
            }
            val iosTest by getting
//            val iosSimulatorArm64Test by getting
//            val iosSimulatorArm64Main by getting
            val iosMain by getting {
                this.dependsOn(commonMain)
                iosTest.dependsOn(this)
//                iosSimulatorArm64Test.dependsOn(this)
//                iosSimulatorArm64Main.dependsOn(this)
            }
            val desktopMain by getting {
                this.dependsOn(commonMain)
            }

            all {
                languageSettings.apply {
                    optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
                    optIn("kotlinx.serialization.ExperimentalSerializationApi")
                    optIn("kotlinx.coroutines.FlowPreview")
                    optIn("kotlin.time.ExperimentalTime")
                    optIn("kotlin.RequiresOptIn")
                    optIn("kotlin.Experimental")
                    optIn("kotlin.ExperimentalStdlibApi")
                    optIn("com.russhwolf.settings.ExperimentalSettingsImplementation")
                }
            }
        }
    }

}

android {
    compileSdk = AppConfig.compileSdk
    defaultConfig {
        minSdk = AppConfig.minSdk
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(11))
}
