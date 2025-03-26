package data.remote.dto.response.groqChatRes


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Usage(
    @SerialName("completion_time")
    val completionTime: Double,
    @SerialName("completion_tokens")
    val completionTokens: Int,
    @SerialName("prompt_time")
    val promptTime: Double,
    @SerialName("prompt_tokens")
    val promptTokens: Int,
    @SerialName("queue_time")
    val queueTime: Double,
    @SerialName("total_time")
    val totalTime: Double,
    @SerialName("total_tokens")
    val totalTokens: Int
)