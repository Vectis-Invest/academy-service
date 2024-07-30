package app.weltis.model

import app.weltis.extensions.updateUrl

data class Topic(
    val name: Map<String, String>,
    val description: Map<String, String>,
    val order: Int,
    val iconUrl: String,
    val available: Boolean,
    val modules: List<Module>,
) {
    fun toMap(env: String): Map<String, Any> =
        mapOf(
            "name" to name,
            "description" to description,
            "order" to order,
            "available" to available,
            "iconUrl" to iconUrl.updateUrl(env)!!,
        )
}
