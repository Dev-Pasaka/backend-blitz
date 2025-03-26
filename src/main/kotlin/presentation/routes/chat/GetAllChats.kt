package presentation.routes.chat

import application.plugins.extractUserId
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*
import presentation.services.ChatService


fun Route.getAllChats(chatService: ChatService) {
    authenticate {
        get {
            val userId = call.extractUserId()
            val result = chatService.getAllChatsHistory(userId)
            call.respond(
                typeInfo = typeInfo<Any>(),
                message = result
            )
        }
    }
}