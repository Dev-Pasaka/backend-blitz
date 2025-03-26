package presentation.services

import domain.usecase.chat.ChatUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

class ChatService(
    private val chatUseCase: ChatUseCase
) {
    suspend fun chat(
        userId: String = "",
        chats: SharedFlow<Map<String, String>>
    ) = chatUseCase(
        userId = userId,
        chats = chats
    )
}