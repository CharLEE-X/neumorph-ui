
object AppConfig {
    const val packageName = "com.adwi.neumorph"
    const val applicationId = "$packageName.android"
    const val compileSdk = 33
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
