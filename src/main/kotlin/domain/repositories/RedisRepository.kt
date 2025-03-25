package domain.repositories

interface RedisRepository {
    suspend fun set(key: String, value: String, expire: Long? = null):Boolean
    suspend fun get(key: String): String?
}