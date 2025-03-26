package domain.repositories

import data.remote.dto.request.groqChat.AiModel
import data.remote.dto.request.groqChat.GroqChatReq
import data.remote.dto.response.groqChatRes.GroqChatRes

interface AiChatRepository {
    suspend fun chat(chat:String, model: AiModel):GroqChatRes
}