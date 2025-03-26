package data.repositories

import com.mongodb.client.model.Filters
import common.Resource
import common.utils.Collections
import common.utils.MongoDBConfig
import domain.entries.ChatHistoryEntity
import domain.repositories.ChatHistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext

class ChatHistoryHistoryRepositoryImpl(
   private val db:MongoDBConfig
):ChatHistoryRepository {
    private val chatHistoryCollection = db.database.getCollection<ChatHistoryEntity>(Collections.CHAT_HISTORY.name)

    override suspend fun createChat(chatHistoryEntity: ChatHistoryEntity): Resource<Any> = withContext(Dispatchers.IO) {
        val chatCreationResult = chatHistoryCollection.insertOne(chatHistoryEntity).wasAcknowledged()
        if (!chatCreationResult) return@withContext Resource.Error(message = "Failed to create chat")
        return@withContext Resource.Success(message = "Chat created successfully", data = null)
    }

    override suspend fun deleteChat(id: String): Resource<Any> = withContext(Dispatchers.IO) {
        val chatDeletionResult = chatHistoryCollection.deleteOne(Filters.eq(ChatHistoryEntity::id.name,  id)).wasAcknowledged()
        if (!chatDeletionResult) return@withContext Resource.Error(message = "Failed to delete chat")
        return@withContext Resource.Success(message = "Chat deleted successfully", data = null)
    }

    override suspend fun deleteAllChats(userId: String): Resource<Any>  = withContext(Dispatchers.IO){
        val chatDeletionResult = chatHistoryCollection.deleteMany(
            Filters.eq(ChatHistoryEntity::userId.name, userId)
        ).wasAcknowledged()

        if (!chatDeletionResult) return@withContext Resource.Error(message = "Failed to delete chats")
        return@withContext Resource.Success(message = "Chats deleted successfully", data = null)
    }

    override suspend fun getChat(id: String): Resource<Any> = withContext(Dispatchers.IO) {
        val chat = chatHistoryCollection.find(Filters.eq(ChatHistoryEntity::id.name, id)).firstOrNull()
            ?: return@withContext Resource.Error(message = "Chat not found")
        return@withContext Resource.Success(message = "Chat found", data = chat)
    }

    override suspend fun getAllChat(userId:String): Resource<Any> = withContext(Dispatchers.IO) {
        val chats = chatHistoryCollection.find(
            Filters.eq(ChatHistoryEntity::userId.name, userId)
        ).toList()
        return@withContext Resource.Success(message = "Chats found", data = chats)
    }
}