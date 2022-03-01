object Versions {
    internal const val gradle = "7.0.0"
    internal const val kotlin = "1.6.10"
    const val dokka = "1.6.10"
    internal const val androidCore = "1.7.0"
    const val compose = "1.2.0-alpha04"
    internal const val composeTooling = "1.1.0"
    internal const val activityCompose = "1.4.0"
    internal const val lifecycle = "2.4.1"
    internal const val composeMaterial = "1.2.0-alpha03"
    const val composeConstraintLayout = "1.0.0"
    internal const val jUnit4 = "4.13.2"
    const val accompanist = "0.21.2-beta"
    const val krottvSliders = "0.1.4"
    const val colorMath = "3.2.0"
}

object Dependencies {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val dokka = "org.jetbrains.dokka:dokka-gradle-plugin:${Versions.dokka}"
    const val dokkaCore = "org.jetbrains.dokka:dokka-core:${Versions.dokka}"
    const val androidXCoreKtx = "androidx.core:core-ktx:${Versions.androidCore}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.composeTooling}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.composeMaterial}"
    const val composeIcons = "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val composeConstraintLayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraintLayout}"
    const val accompanist = "com.google.accompanist:accompanist-flowlayout:${Versions.accompanist}"
    const val krottvSliders = "com.github.krottv:compose-sliders:${Versions.krottvSliders}"
    const val colorMath = "com.github.ajalt.colormath:colormath:${Versions.colorMath}"
    // Test
    const val jUnit4 = "junit:junit:${Versions.jUnit4}"
    const val composeTest = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    // Debug
    const val composeToolingDebug = "androidx.compose.ui:ui-tooling:${Versions.composeTooling}"
    const val composeManifestDebug = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
}
