package presentation.routes.chat

import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*
import presentation.dtos.DefaultResponse
import presentation.services.ChatService

fun Route.deleteChat(chatService: ChatService) {
    authenticate {
        delete ("{id}"){
            val id = call.parameters["id"] ?: return@delete call.respond(
                typeInfo = typeInfo<Any>(),
                message = DefaultResponse(
                    status = false,
                    httpStatusCode = HttpStatusCode.BadRequest.value,
                    message = "Parameter id is required",
                    data = null
                )
            )
            val result = chatService.deleteChatHistory(id)
            call.respond(
                typeInfo = typeInfo<Any>(),
                message = result
            )
        }
    }
}