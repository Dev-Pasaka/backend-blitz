package data.remote.dto.response.groqChatRes


import domain.entries.ChatHistoryEntity
import domain.models.ChatResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroqChatRes(
    @SerialName("choices")
    val choices: List<Choice>,
    @SerialName("created")
    val created: Int,
    @SerialName("id")
    val id: String,
    @SerialName("model")
    val model: String,
    @SerialName("object")
    val objectX: String,
    @SerialName("system_fingerprint")
    val systemFingerprint: String,
    @SerialName("usage")
    val usage: Usage,
    @SerialName("x_groq")
    val xGroq: XGroq
){
    fun toChatResponse() = ChatResponse(
        model = model,
        content = choices.firstOrNull()?.message?.content ?: "No answer form the model",
        duration = usage.completionTime
    )

    fun toChatHistoryEntity() = ChatHistoryEntity(
        chatId = id,
        model = model,
        content = choices.firstOrNull()?.message?.content ?: "No answer form the model",
        duration = usage.completionTime,
    )
}