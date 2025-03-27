package presentation.controllers

import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import presentation.routes.pages.blitzApp

fun Route.pageController(){
    route("app") {
        get{
            call.respondHtml {
                blitzApp()
            }
        }
    }
}