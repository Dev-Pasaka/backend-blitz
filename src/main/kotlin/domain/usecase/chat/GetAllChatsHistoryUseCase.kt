package domain.usecase.chat

import common.Resource
import common.utils.Type
import common.utils.logger
import domain.repositories.ChatHistoryRepository
import presentation.dtos.DefaultResponse

class GetAllChatsHistoryUseCase(
    private val chatHistoryRepository: ChatHistoryRepository
) {
    suspend operator fun invoke(userId:String) = try {
        when(val result = chatHistoryRepository.getAllChat(userId)) {
            is Resource.Success ->  DefaultResponse(
                status = true,
                message = "Chats history fetched successfully",
                data = result.data
            )
            is Resource.Error -> DefaultResponse(
                status = false,
                message = result.message ?: "An error occurred",
                data = null
            )
        }
    }catch (e:Exception){
        e.message?.logger(Type.WARN) ?: e.printStackTrace()
        DefaultResponse(
            status = false,
            message = e.message ?: "An error occurred",
            data = null
        )
    }
}