package app.weltis.model

import app.weltis.extensions.updateUrl

data class Lesson(
    val title: Map<String, String>,
    val order: Int,
    val available: Boolean,
    val thumbnailUrl: String,
    val materials: List<Material>,
) {
    fun toMap(
        moduleId: String,
        nextLessonId: String?,
        nextLessonTitle: Map<String, String>?,
        nextLessonThumbnailUrl: String?,
        env: String,
    ): Map<String, Any> =
        mapOf(
            "title" to title,
            "order" to order,
            "available" to available,
            "thumbnailUrl" to thumbnailUrl.updateUrl(env),
            "moduleId" to moduleId,
            "nextLessonId" to nextLessonId,
            "nextLessonTitle" to nextLessonTitle,
            "nextLessonThumbnailUrl" to nextLessonThumbnailUrl.updateUrl(env),
        ).filterValues { it != null } as Map<String, Any>
}
