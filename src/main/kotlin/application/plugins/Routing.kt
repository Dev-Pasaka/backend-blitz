package application.plugins

import domain.repositories.RedisRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import presentation.controllers.userController
import presentation.services.UserService


fun Application.configureRouting() {
    val userService:UserService by inject()
    val redis: RedisRepository by inject()
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: ${cause.printStackTrace()}" , status = HttpStatusCode.InternalServerError)
        }
    }
    routing {

        get("/") {
            redis.set(key = "test", value = "test")
            val result = redis.get("test")
            call.respondText("Hello World! $result")
        }
        userController(userService)

    }
}
