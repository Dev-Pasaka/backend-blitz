package domain.entries

import org.bson.types.ObjectId
import java.time.Instant

data class ChatHistoryEntity(
    val id:String = ObjectId().toString(),
    val userId:String,
    val title: String,
    val content:String,
    val createdAt:String = Instant.now().toString(),
)
