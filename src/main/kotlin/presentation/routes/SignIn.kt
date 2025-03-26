package presentation.routes

import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*
import presentation.dtos.requests.SignInReq
import presentation.services.UserService



fun Route.signIn(userService: UserService){
    post("sign-in") {
        val body = call.receive<SignInReq>()
        val result = userService.signIn(body)
        call.respond(typeInfo = typeInfo<Any>(), message = result)
    }
}