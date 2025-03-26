package common.utils

import com.typesafe.config.ConfigFactory



object GroqConfig {
    private val load = ConfigFactory.load()
    val apiKey = System.getenv("GROQ_API_KEY") ?: ""
    //val baseUrl = load.getString("ktor.groq") ?: ""
    const val BASEURL = "https://api.groq.com/openai/v1/chat/completions"

}