package app.weltis.extensions

fun String?.updateUrl(env: String): String? {
    return when (env) {
        "development" -> this
        "staging" -> this?.replace("development", "staging")
        "production" -> this?.replace("development", "production")
        else -> this
    }
}
