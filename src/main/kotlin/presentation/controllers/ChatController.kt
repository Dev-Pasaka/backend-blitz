package presentation.controllers

import io.ktor.server.routing.*
import presentation.routes.chat.*
import presentation.services.ChatService

fun Route.chatController(chatService: ChatService) {
    route("chat") {
        chat(chatService)
        getAllChats(chatService)
        getChatHistory(chatService)
        deleteAllChatsHistory(chatService)
        deleteChat(chatService)
    }
}