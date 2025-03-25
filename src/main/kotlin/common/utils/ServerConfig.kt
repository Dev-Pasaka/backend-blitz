package common.utils

import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory
import org.slf4j.Logger

object ServerConfig {
    private val load = ConfigFactory.load()
    val port: Int = load.getString("ktor.deployment.port").toIntOrNull() ?: 8080
    val host:String = load.getString("ktor.deployment.host") ?: "0.0.0.0"
    val logger:Logger  =  LoggerFactory.getLogger(load.getString("ktor.server.logger") ?: "")
    val apiVersion = load.getString("ktor.server.apiVersion") ?: ""
    val serverUrl = System.getenv("SERVER_URL") ?: "http://localhost:8080"
}