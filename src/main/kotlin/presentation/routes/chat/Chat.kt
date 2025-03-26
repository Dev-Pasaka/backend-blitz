package presentation.routes.chat

import application.plugins.extractUserId
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import presentation.services.ChatService

fun Route.chat(chatService: ChatService) {
    authenticate {
        webSocket {
            val userId = call.extractUserId()
            val userChats = Channel<Map<String, String>>(capacity = 10) // Bounded channel to avoid memory leaks
            val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

            // Launch chat processing in a separate coroutine
            val job = scope.launch {
                chatService.chat(
                    userId = userId,
                    chats = userChats.consumeAsFlow().shareIn(scope, SharingStarted.Lazily)
                ).collect { resource ->
                    outgoing.send(Frame.Text(resource.toDefaultResponse().toJson()))
                }
            }

            try {
                for (frame in incoming) {
                    if (frame is Frame.Text) {
                        val text = frame.readText()
                        userChats.send(mapOf("chat" to text))

                        if (text.equals("bye", ignoreCase = true)) {
                            close(CloseReason(CloseReason.Codes.NORMAL, "Client said BYE"))
                            break
                        }
                    }
                }
            } finally {
                job.cancel() // Ensure chat processing is stopped when the WebSocket closes
                userChats.close() // Clean up resources
            }
        }
    }
}
