package data.remote.dto.request.groqChat


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroqChatReq(
    val model: String,
    val messages: List<Message>,
)

@Serializable
sealed class AiModel(val name:String){
    data object Gemma29B:AiModel(name = "gemma2-9b-it")
    data object Llama3Instant8B:AiModel(name = "llama-3.1-8b-instant")
    data object Llama3Instant70B:AiModel(name = "llama3-70b-8192")
    data object Qwen32B:AiModel(name = "qwen-qwq-32b")
    data object DeepSeekR1DistillQwen32B:AiModel(name = "deepseek-r1-distill-qwen-32b")
    data object DeepSeekR1DistillLlama70B:AiModel(name = "deepseek-r1-distill-llama-70b-specdec")
}