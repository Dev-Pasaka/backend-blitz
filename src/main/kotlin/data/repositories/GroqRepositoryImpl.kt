package data.repositories

import common.utils.GroqConfig
import common.utils.HttpClient
import data.remote.dto.request.groqChat.AiModel
import data.remote.dto.request.groqChat.GroqChatReq
import data.remote.dto.request.groqChat.Message
import data.remote.dto.response.groqChatRes.GroqChatRes
import domain.repositories.AiChatRepository
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GroqRepositoryImpl(
    private val httpClient: HttpClient,
    private val groqConfig:GroqConfig
):AiChatRepository {
    override suspend fun chat(chat:String, model:AiModel): GroqChatRes = withContext(Dispatchers.IO) {
        httpClient.client.post(groqConfig.BASEURL){
            headers {
                append(HttpHeaders.Authorization, "Bearer ${groqConfig.apiKey}")
                append(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            val requestBody = GroqChatReq(
                model = model.name,
                messages = listOf(
                    Message(
                        role = "user",
                        content = chat,
                    )
                )
            )
            setBody(requestBody)
        }.body()
    }
}

suspend fun main(){
    val result = GroqRepositoryImpl(HttpClient,GroqConfig).chat(
        model = AiModel.Qwen32B,
        chat = "Hello what is llms"
    )
    println(result)
}


