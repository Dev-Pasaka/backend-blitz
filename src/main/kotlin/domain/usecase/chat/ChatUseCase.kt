package domain.usecase.chat

import common.Resource
import common.utils.Type
import common.utils.logger
import data.remote.dto.request.groqChat.AiModel
import domain.repositories.AiChatRepository
import domain.repositories.ChatHistoryRepository
import kotlinx.coroutines.flow.*
import java.util.*

class ChatUseCase(
    private val chatHistoryRepository: ChatHistoryRepository,
    private val aiChatRepository: AiChatRepository,
) {
    operator fun invoke(
        userId: String = "",
        chats: SharedFlow<Map<String, String>>,
    ): Flow<Resource<Any>> = flow {
        emit(
            Resource.Success(
                message = "Connected to backend blitz chat session.",
                data = null
            )
        )

        val modelMap = mapOf(
            "1" to AiModel.Qwen32B,
            "2" to AiModel.Llama3Instant70B,
            "3" to AiModel.DeepSeekR1DistillLlama70B,
            "4" to AiModel.DeepSeekR1DistillQwen32B,
            "5" to AiModel.Llama3Instant8B,
            "6" to AiModel.Gemma29B
        )

        emit(
            Resource.Success(
                message = "Kindly choose the model you would like to chat with. If no choice is made, Llama3Instant70B will be selected automatically.",
                data = mapOf(
                    "status" to "complete",
                    "models" to modelMap
                )
            )
        )

        val selectedModel: AiModel = selectModel(
            userId = userId,
            chats = chats,
            modelMap = modelMap
        ) ?: AiModel.Llama3Instant70B

        "User selected model: ${selectedModel.name}" logger (Type.INFO)

        emit(
            Resource.Success(
                message = "Selected model: ${selectedModel.name}",
                data = null
            )
        )

        chatWithModel(
            userId = userId,
            model = selectedModel,
            chats = chats
        ).collect { emit(it) }
    }

    private suspend fun selectModel(
        userId: String,
        chats: SharedFlow<Map<String, String>>,
        modelMap: Map<String, AiModel>,

    ): AiModel? {
        return chats.mapNotNull { chat ->
            "Selecting model from chat: $chat" logger (Type.INFO)
            chat.values.firstNotNullOfOrNull { input ->
                val result = input.toIntOrNull()?.let { modelMap[it.toString()] } ?: modelMap.values.find { model ->
                        input.lowercase(Locale.getDefault()).contains(model.name.lowercase())
                    }
                if (result == null) {
                    chatWithModel(
                        userId = userId,
                        model =  AiModel.Llama3Instant70B,
                        chats = chats
                    )
                }
                result
            }
        }.firstOrNull()
    }

    private fun chatWithModel(
        userId: String,
        model: AiModel,
        chats: SharedFlow<Map<String, String>>,
    ): Flow<Resource<Any>> = flow {
        chats.collect { chat ->
            "Chatting with ${model.name} : Chat -> $chat" logger (Type.INFO)

            emit(
                Resource.Success(
                    message = "${model.name} is typing...",
                    data = mapOf("status" to "loading")
                )
            )

            val input: String = chat.values.firstOrNull() ?: return@collect
            val response = aiChatRepository.chat(model = model, chat = input)

            chatHistoryRepository.createChat(
                response.toChatHistoryEntity().copy(userId = userId)
            )

            emit(
                Resource.Success(
                    message = "Response from ${model.name}",
                    data = mapOf(
                        "status" to "complete",
                        "message" to response.toChatResponse()
                    )
                )
            )
        }
    }
}
