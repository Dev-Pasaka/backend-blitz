package data.repositories

import common.utils.RedisConfig
import domain.repositories.RedisRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RedisRepositoryImpl(
    private val redis: RedisConfig
):RedisRepository {
    override suspend fun set(key: String, value: String, expire: Long?): Boolean  = withContext(Dispatchers.IO){
        redis.client.use { client ->
            client.set(key, value)
            expire?.let { client.expire(key, it) }
        }
        true
    }

    override suspend fun get(key: String): String? = withContext(Dispatchers.IO){
        redis.client.use { client ->
            return@withContext client.get(key)
        }
    }


}

