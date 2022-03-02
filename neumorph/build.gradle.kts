plugins {
    id(Plugins.ANDROID_LIBRARY)
    kotlin(Plugins.ANDROID)
    `maven-publish`
    id(Plugins.DOKKA)
}

android {
    namespace = "${AppConfig.namespace}.neumorph.android"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        testInstrumentationRunner = AppConfig.testRunner
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
}

val dokkaOutputDir = "$rootDir/docs"

tasks {
    dokkaHtml {
        outputDirectory.set(file(dokkaOutputDir))
    }
}

dependencies {
    compileOnly(Dependencies.dokkaCore)
    implementation(Dependencies.colorMath)
    implementation(Dependencies.lifecycle)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.accompanist)
    implementation(Dependencies.composeIcons)
    implementation(Dependencies.krottvSliders)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.androidXCoreKtx)
    implementation(Dependencies.composeActivity)
    implementation(Dependencies.composeToolingPreview)
    api(Dependencies.composeConstraintLayout)
    debugImplementation(Dependencies.composeToolingDebug)
    debugImplementation(Dependencies.composeManifestDebug)

    testImplementation(Dependencies.jUnit4)
    testImplementation(Dependencies.composeTest)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = AppConfig.Maven.groupId
                artifactId = AppConfig.Maven.artifactId
                version = AppConfig.Maven.version
            }
        }
    }
}
