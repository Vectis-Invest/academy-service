package app.weltis.model

import app.weltis.extensions.updateUrl
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonValue

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "materialType", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = Material.Text::class, name = "text"),
    JsonSubTypes.Type(value = Material.Poll::class, name = "poll"),
    JsonSubTypes.Type(value = Material.Story::class, name = "story"),
    JsonSubTypes.Type(value = Material.Quiz::class, name = "quiz"),
)
sealed class Material {
    abstract val materialType: MaterialType
    abstract val order: Int
    abstract val title: Map<String, String>

    open fun toMap(
        lessonId: String,
        nextMaterialId: String?,
        env: String,
    ): Map<String, Any?> =
        mapOf(
            "type" to materialType.name,
            "title" to title,
            "order" to order,
            "nextMaterialId" to nextMaterialId,
            "lessonId" to lessonId,
        ).filterValues { it != null }

    data class Text(
        override val materialType: MaterialType = MaterialType.TEXT,
        override val order: Int,
        override val title: Map<String, String>,
        val content: Map<String, String>,
        val backgroundColor: String? = null,
        val pictureUrl: String? = null,
    ) : Material() {
        override fun toMap(
            lessonId: String,
            nextMaterialId: String?,
            env: String,
        ): Map<String, Any?> =
            super.toMap(lessonId, nextMaterialId, env) +
                mapOf(
                    "content" to content,
                    "backgroundColor" to backgroundColor,
                    "pictureUrl" to pictureUrl.updateUrl(env),
                ).filterValues { it != null }.mapValues { it.value!! }
    }

    data class Poll(
        override val materialType: MaterialType = MaterialType.POLL,
        override val order: Int,
        override val title: Map<String, String>,
        val description: Map<String, String>,
        val choices: Map<String, PollOption>,
    ) : Material() {
        override fun toMap(
            lessonId: String,
            nextMaterialId: String?,
            env: String,
        ): Map<String, Any?> =
            super.toMap(lessonId, nextMaterialId, env) +
                mapOf(
                    "description" to description,
                    "choices" to choices,
                )
    }

    data class PollOption(
        val text: Map<String, String>,
        val votes: Int,
    )

    data class Story(
        override val materialType: MaterialType = MaterialType.STORY,
        override val order: Int,
        override val title: Map<String, String>,
        val storyUrl: String,
    ) : Material() {
        override fun toMap(
            lessonId: String,
            nextMaterialId: String?,
            env: String,
        ): Map<String, Any?> =
            super.toMap(lessonId, nextMaterialId, env) +
                mapOf(
                    "storyUrl" to storyUrl.updateUrl(env),
                )
    }

    data class Quiz(
        override val materialType: MaterialType = MaterialType.QUIZ,
        override val order: Int,
        override val title: Map<String, String>,
        val choices: Map<String, List<String>>,
        val correctAnswer: Int,
        val correctAnswerText: Map<String, String>,
        val wrongAnswerText: Map<String, String>,
    ) : Material() {
        override fun toMap(
            lessonId: String,
            nextMaterialId: String?,
            env: String,
        ): Map<String, Any?> =
            super.toMap(lessonId, nextMaterialId, env) +
                mapOf(
                    "choices" to choices,
                    "correctAnswer" to correctAnswer,
                    "correctAnswerText" to correctAnswerText,
                    "wrongAnswerText" to wrongAnswerText,
                )
    }
}

enum class MaterialType(
    @JsonValue val value: String,
) {
    TEXT("text"),
    STORY("story"),
    POLL("poll"),
    QUIZ("quiz"),
}
