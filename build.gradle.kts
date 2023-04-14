plugins {
    id(Plugins.COMPOSE_MULTIPLATFORM) version Versions.composeMultiplatform apply false
    id(Plugins.DOKKA) version Versions.dokka apply false
}

subprojects {
    apply(plugin = Plugins.DOKKA)
}
