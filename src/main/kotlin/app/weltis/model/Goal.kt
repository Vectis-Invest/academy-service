package app.weltis.model

import app.weltis.extensions.updateUrl

data class Goal(
    val name: Map<String, String>,
    val iconUrl: String,
    val order: Int,
) {
    fun toMap(env: String): Map<String, Any> =
        mapOf(
            "name" to name,
            "iconUrl" to iconUrl.updateUrl(env)!!,
            "order" to order,
        )
}
