package app.weltis.model

import app.weltis.extensions.updateUrl

data class RecommendedLesson(
    val lessonId: String,
    val lessonTitle: Map<String, String>,
    val lessonThumbnailUrl: String,
    val topicName: Map<String, String>,
    val moduleName: Map<String, String>,
) {
    fun toMap(env: String): Map<String, Any> =
        mapOf(
            "lessonId" to lessonId,
            "lessonTitle" to lessonTitle,
            "lessonThumbnailUrl" to lessonThumbnailUrl.updateUrl(env)!!,
            "topicName" to topicName,
            "moduleName" to moduleName,
        )
}
