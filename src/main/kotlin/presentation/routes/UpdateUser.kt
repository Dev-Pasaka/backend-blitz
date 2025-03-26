package presentation.routes

import application.plugins.extractUserId
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*
import presentation.dtos.requests.UpdateUserReq
import presentation.services.UserService

fun Route.updateUser(userService: UserService) {
    authenticate {
        put {
            val userId = call.extractUserId()
            val body = call.receive<UpdateUserReq>()
            val result = userService.updateUser(userId, body)
            call.respond(
                typeInfo = typeInfo<Any>(),
                message = result
            )
        }
    }
}