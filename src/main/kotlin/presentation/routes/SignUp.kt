package presentation.routes

import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*
import presentation.dtos.requests.CreateUserReq
import presentation.services.UserService

fun Route.signUp(userService: UserService){
    post("sign-up") {
        val body = call.receive<CreateUserReq>()
        val result = userService.createUser(body)
        call.respond(typeInfo = typeInfo<Any>(), message = result)
    }
}