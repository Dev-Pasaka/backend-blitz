package common.utils

import com.mongodb.kotlin.client.coroutine.MongoClient

object MongoDBConfig {
    private val username:String = System.getenv("MONGODBUSERNAME") ?: ""
    private val password:String = System.getenv("MONGODBPASSWORD") ?: ""
    private val databaseName:String = System.getenv("DATABASENAME") ?: ""
    private val host:String = System.getenv("HOST") ?: ""

    private val url = "mongodb://$username:$password@$host:27017/"
    private val mongoClient by lazy {  MongoClient.create(url)}
    val database = mongoClient.getDatabase(databaseName)

}

enum class Collections {
    USER,
    CHAT_HISTORY
}