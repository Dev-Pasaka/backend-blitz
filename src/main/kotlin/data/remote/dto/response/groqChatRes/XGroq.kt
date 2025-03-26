package data.remote.dto.response.groqChatRes


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class XGroq(
    @SerialName("id")
    val id: String
)