package domain.repositories

import common.Resource
import domain.entries.ChatHistoryEntity

interface ChatHistoryRepository {
    suspend fun createChat(chatHistoryEntity:ChatHistoryEntity):Resource<Any>
    suspend fun deleteChat(id:String):Resource<Any>
    suspend fun deleteAllChats(userId:String):Resource<Any>
    suspend fun getChat(id:String):Resource<Any>
    suspend fun getAllChat(userId:String):Resource<Any>
}