plugins {
    id(Plugins.ANDROID_APPLICATION)
    kotlin(Plugins.ANDROID)
}

android {
    namespace = "${AppConfig.namespace}.neumorph"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.testRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(Modules.NEUMORPH))

    implementation(Dependencies.lifecycle)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeIcons)
    implementation(Dependencies.androidXCoreKtx)
    implementation(Dependencies.composeActivity)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeToolingPreview)
    debugImplementation(Dependencies.composeToolingDebug)
    debugImplementation(Dependencies.composeManifestDebug)

//    implementation("com.github.adrianwitaszak:neumorph-ui:0.7.0")

    testImplementation(Dependencies.jUnit4)
    testImplementation(Dependencies.composeTest)
}
