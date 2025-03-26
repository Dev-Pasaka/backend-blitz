package presentation.controllers

import io.ktor.server.routing.*
import presentation.routes.user.getUserProfile
import presentation.routes.user.signIn
import presentation.routes.user.signUp
import presentation.routes.user.updateUser
import presentation.services.UserService

fun Route.userController(userService: UserService) {
    route("user") {
        signUp(userService)
        signIn(userService)
        getUserProfile(userService)
        updateUser(userService)
    }
}

