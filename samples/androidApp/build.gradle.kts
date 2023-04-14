@file:Suppress("UnstableApiUsage")
plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.COMPOSE_MULTIPLATFORM)
}

android {
    namespace = location(Modules.SAMPLE)
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
    }
    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.jetpackCompose
    }
}

dependencies {
    implementation(project(Modules.COMPONENTS))
    implementation(project(Modules.EFFECT))

//    implementation(Dependencies.AndroidX.lifecycle)
    implementation(Dependencies.JetpackCompose.activity)
    implementation(compose.ui)
    implementation(compose.material)
    implementation(compose.materialIconsExtended)
    implementation(compose.preview)
    implementation(Dependencies.JetpackCompose.constraintLayout)

//    implementation("com.github.adrianwitaszak:neumorph-ui:0.7.7")
}
