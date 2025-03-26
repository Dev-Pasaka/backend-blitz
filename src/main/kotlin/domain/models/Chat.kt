package domain.models

import org.bson.types.ObjectId
import java.time.Instant

data class Chat(
    val id:String = ObjectId().toString(),
    val model:String,
    val content:String,
    val duration:Double,
    val createdAt:String = Instant.now().toString(),
)
