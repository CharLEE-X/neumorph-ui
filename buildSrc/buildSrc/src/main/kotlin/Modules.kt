object Modules {
    const val SAMPLE = ":samples:androidApp"
    const val EFFECT = ":neumorph:effect"
    const val COMPONENTS = ":neumorph:components"
}

fun String.name() = drop(1).replace(":", ".")
fun location(moduleName: String) = AppConfig.packageName + moduleName.name()
