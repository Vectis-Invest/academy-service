package app.weltis.config

import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import org.jboss.logging.Logger

@Provider
class GlobalExceptionHandler : ExceptionMapper<Throwable> {
    private val log: Logger = Logger.getLogger(GlobalExceptionHandler::class.java)

    override fun toResponse(exception: Throwable): Response {
        log.error("An error occurred processing the request", exception)

        val errorMessage =
            when (exception) {
                is IllegalArgumentException -> "Invalid argument: ${exception.message}"
                else -> "An unexpected error occurred: ${exception.message}"
            }

        return Response
            .status(Response.Status.BAD_REQUEST)
            .entity(mapOf("error" to errorMessage))
            .build()
    }
}
