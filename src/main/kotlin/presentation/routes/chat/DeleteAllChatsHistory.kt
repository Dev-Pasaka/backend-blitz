package presentation.routes.chat

import application.plugins.extractUserId
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*
import presentation.services.ChatService


fun Route.deleteAllChatsHistory(chatService: ChatService) {
    authenticate {
        delete {
            val userId = call.extractUserId()
            val result = chatService.deleteAllChatHistory(userId)
            call.respond(
                typeInfo = typeInfo<Any>(),
                message = result
            )
        }
    }
}