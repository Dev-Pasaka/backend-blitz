package presentation.services

import domain.usecase.chat.*
import kotlinx.coroutines.flow.SharedFlow

class ChatService(
    private val chatUseCase: ChatUseCase,
    private val getAllChatsHistoryUseCase: GetAllChatsHistoryUseCase,
    private val getChatHistoryUseCase: GetChatHistoryUseCase,
    private val deleteAllChatHistoryUseCase: DeleteAllChatHistoryUseCase,
    private val deleteChatHistoryUseCase: DeleteChatHistoryUseCase
) {
     fun chat(
        userId: String = "",
        chats: SharedFlow<Map<String, String>>
    ) = chatUseCase(
        userId = userId,
        chats = chats
    )

    suspend fun getAllChatsHistory(userId: String) = getAllChatsHistoryUseCase(userId)
    suspend fun getChatHistory(chatId: String) = getChatHistoryUseCase(chatId)

    suspend fun deleteAllChatHistory(userId: String) = deleteAllChatHistoryUseCase(userId)
    suspend fun deleteChatHistory(chatId: String) = deleteChatHistoryUseCase(chatId)

}