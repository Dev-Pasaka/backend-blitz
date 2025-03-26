package domain.entries

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
)
