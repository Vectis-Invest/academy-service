package app.weltis

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
open class GreetingResourceTest {
    @Test
    fun testHelloEndpoint() {
        given()
            .`when`()
            .get("/hello")
            .then()
            .statusCode(200)
            .body(`is`("Welcome to Weltis Academy Admin REST"))
    }
}
