package domain.models

data class ChatResponse(
    val model:String,
    val content:String,
    val duration:Double,
)