package app.weltis.model

import app.weltis.extensions.updateUrl

data class Material(
    val materialType: String,
    val order: Int,
    val title: Map<String, String>,
    val content: Map<String, String>? = null,
    val pictureUrl: String? = null,
    val backgroundColor: String? = null,
    val description: Map<String, String>? = null,
    val options: Map<String, PollOption>? = null,
    val choices: Map<String, List<String>>? = null,
    val storyUrl: String? = null,
    val correctAnswer: Int? = null,
    val correctAnswerText: Map<String, String>? = null,
    val wrongAnswerText: Map<String, String>? = null,
) {
    fun toMap(
        lessonId: String,
        nextMaterialId: String?,
        env: String,
    ): Map<String, Any?> =
        mapOf(
            "type" to materialType,
            "title" to title,
            "order" to order,
            "nextMaterialId" to nextMaterialId,
            "lessonId" to lessonId,
            "content" to content,
            "backgroundColor" to backgroundColor,
            "pictureUrl" to pictureUrl.updateUrl(env),
            "description" to description,
            "options" to options,
            "storyUrl" to storyUrl.updateUrl(env),
            "choices" to choices,
            "correctAnswer" to correctAnswer,
            "correctAnswerText" to correctAnswerText,
            "wrongAnswerText" to wrongAnswerText,
        ).filterValues { it != null }
}

data class PollOption(
    val text: Map<String, String>,
    val votes: Int,
)
