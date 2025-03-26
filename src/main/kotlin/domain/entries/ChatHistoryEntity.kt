package domain.entries

import domain.models.Chat
import org.bson.types.ObjectId
import java.time.Instant

data class ChatHistoryEntity(
    val id:String = ObjectId().toString(),
    val chatId:String,
    val model:String,
    val userId:String = "",
    val content:String,
    val duration:Double,
    val createdAt:String = Instant.now().toString(),
){
    fun toChat() = Chat(
        id = id,
        model = model,
        content = content,
        duration = duration,
        createdAt = createdAt
    )
}
