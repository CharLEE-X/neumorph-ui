
object AppConfig {
    const val namespace = "com.adwi"
    const val applicationId = "com.adwi.sample"
    const val compileSdk = 32
    const val targetSdk = compileSdk
    const val minSdk = 21
    const val versionCode = 1
    const val versionName = Maven.version
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"

    const val jvmTarget = "11"

    object Maven {
        const val groupId = "com.github.adrianwitaszak"
        const val artifactId = "neumorph-ui"
        const val version = "0.7.7"
    }
}