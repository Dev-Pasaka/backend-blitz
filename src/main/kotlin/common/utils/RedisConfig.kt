package common.utils

import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
object RedisConfig {
    private val redisHost: String = System.getenv("REDIS_HOST") ?: "redis://localhost"
    private val redisPort: Int = System.getenv("REDIS_PORT")?.toIntOrNull() ?: 6379
    private var pool = JedisPool("$redisHost:$redisPort")
    private val jedis: Jedis = pool.resource
    val client = jedis
}