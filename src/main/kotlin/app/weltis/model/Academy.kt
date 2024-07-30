package app.weltis.model

import app.weltis.extensions.updateUrl
import com.google.cloud.firestore.FieldValue

data class Academy(
    val getStartedStoriesUrls: List<String>,
    val topics: List<Topic>,
) {
    fun toMap(
        now: FieldValue,
        env: String,
    ): Map<String, Any> =
        mapOf(
            "lastUpdated" to now,
            "getStartedStoriesUrls" to getStartedStoriesUrls.map { storyUrl -> storyUrl.updateUrl(env) },
        )
}
