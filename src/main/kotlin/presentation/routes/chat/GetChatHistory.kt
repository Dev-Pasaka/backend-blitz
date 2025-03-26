package presentation.routes.chat

import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*
import presentation.dtos.DefaultResponse
import presentation.services.ChatService

fun Route.getChatHistory(chatService: ChatService) {
    authenticate {
        get ("{id}"){
            val id = call.parameters["id"] ?: return@get call.respond(
                typeInfo = typeInfo<Any>(),
                message = DefaultResponse(
                    status = false,
                    httpStatusCode = HttpStatusCode.BadRequest.value,
                    message = "Parameter id is required",
                    data = null
                )
            )
            val result = chatService.getChatHistory(id)
            call.respond(
                typeInfo = typeInfo<Any>(),
                message = result
            )
        }
    }
}