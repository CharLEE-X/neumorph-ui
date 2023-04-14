object Versions {
    const val accompanist = "0.21.2-beta"
    const val activityCompose = "1.4.0"
    const val agp = "7.4.1"
    const val androidCore = "1.7.0"
    const val colorMath = "3.2.1"
    const val composeMultiplatform = "1.4.0"
    const val composeMaterial = "1.2.0-alpha03"
    const val composeConstraintLayout = "1.0.1"
    const val dokka = "1.8.10"
    const val jetpackCompose = "1.4.1"
    const val kotlin = "1.8.20"
    const val krottvSliders = "0.1.4"
    const val lifecycle = "2.4.1"
}

object Dependencies {
    object Kotlin {
        const val gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val dokkaGradle = "org.jetbrains.dokka:dokka-gradle-plugin:${Versions.dokka}"
        const val dokkaCore = "org.jetbrains.dokka:dokka-core:${Versions.dokka}"
    }

    object AndroidX {
        const val gradle = "com.android.tools.build:gradle:${Versions.agp}"
        const val androidXCoreKtx = "androidx.core:core-ktx:${Versions.androidCore}"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    }

    object JetpackCompose {
        const val activity = "androidx.activity:activity-compose:${Versions.activityCompose}"
        const val ui = "androidx.compose.ui:ui:${Versions.jetpackCompose}"
        const val material = "androidx.compose.material:material:${Versions.composeMaterial}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraintLayout}"
    }

    object ComposeMultiplatform {
        const val krottvSliders = "com.github.krottv:compose-sliders:${Versions.krottvSliders}"
        const val colorMath = "com.github.ajalt.colormath:colormath:${Versions.colorMath}"
    }
}
