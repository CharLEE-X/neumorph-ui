plugins {
    `kotlin-dsl`
    id(Plugins.COMPOSE_MULTIPLATFORM) version Versions.composeMultiplatform apply false
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(Dependencies.Kotlin.gradle)
    implementation(Dependencies.AndroidX.gradle)
}

kotlin {
    sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}
