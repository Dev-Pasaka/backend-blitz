package presentation.controllers

import io.ktor.server.routing.*
import presentation.routes.getUserProfile
import presentation.routes.signIn
import presentation.routes.signUp
import presentation.services.UserService

fun Route.userController(userService: UserService) {
    route("user") {
        signUp(userService)
        signIn(userService)
        getUserProfile(userService)
    }
}

