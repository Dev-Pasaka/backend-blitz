package presentation.routes

import application.plugins.extractUserId
import common.utils.Type
import common.utils.logger
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*
import presentation.services.UserService


fun Route.getUserProfile(userService: UserService){
    authenticate {
        this.attributes.toString() logger(Type.INFO)
        get {
            val userId = call.extractUserId()
            val result = userService.getUserProfile(userId)
            call.respond(
                typeInfo = typeInfo<Any>(),
                message = result
            )
        }
    }
}