package presentation.controllers

import io.ktor.server.routing.*
import presentation.routes.signUp
import presentation.services.UserService

fun Route.userController(userService: UserService) {
    route("user"){
        signUp(userService)
    }

}

