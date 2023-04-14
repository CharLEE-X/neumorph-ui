plugins {
    id(Plugins.NEU_MULTIPLATFORM)
    id(Plugins.COMPOSE_MULTIPLATFORM)
}

kotlin.sourceSets {
    named("commonMain") {
        dependencies {
            implementation(compose.dependencies.ui)
            implementation(compose.dependencies.animation)
            implementation(compose.dependencies.material)
        }
    }
    named("androidMain") {
        dependencies {
            implementation(Dependencies.JetpackCompose.ui)
        }
    }
}

android {
    namespace = location(Modules.EFFECT)
}
