package app.weltis.service

import app.weltis.model.Academy
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.config.inject.ConfigProperty
import java.util.concurrent.ExecutionException

@ApplicationScoped
class InitializeAcademyService(
    @ConfigProperty(name = "app.environment")
    private val environment: String,
) {
    private companion object {
        private const val VERSION = "v1"
        private const val ACADEMY_COLLECTION = "academy"
        private const val TOPICS_COLLECTION = "topics"
        private const val MODULE_COLLECTION = "modules"
        private const val LESSON_COLLECTION = "lessons"
        private const val MATERIAL_COLLECTION = "materials"
        private const val GOALS_COLLECTION = "goals"
        private const val USER_ACADEMY_COLLECTION = "user_academy"
    }

    fun readFromFileAndStoreAcademy(): Academy {
        try {
            val academy = Academy(emptyList(), emptyList())
            println("Academy stored to Firestore")
            return academy
        } catch (e: Exception) {
            when (e) {
                is InterruptedException, is ExecutionException -> {
                    println("Error storing academy data to Firestore: ${e.message}")
                    Thread.currentThread().interrupt()
                }

                else -> println("An unexpected error occurred: ${e.message}")
            }
        }
        return Academy(emptyList(), emptyList())
    }
}
