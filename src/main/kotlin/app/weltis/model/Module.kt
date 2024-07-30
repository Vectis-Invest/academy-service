package app.weltis.model

data class Module(
    val name: Map<String, String>,
    val order: Int,
    val available: Boolean,
    val lessons: List<Lesson>,
) {
    fun toMap(
        topicId: String,
        nextModuleId: String?,
    ): Map<String, Any> =
        mapOf(
            "name" to name,
            "order" to order,
            "available" to available,
            "topicId" to topicId,
            "nextModuleId" to nextModuleId,
        ).filterValues { it != null } as Map<String, Any>
}
