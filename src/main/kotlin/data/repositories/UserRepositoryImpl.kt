package data.repositories

import JWTConfig
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import common.Resource
import common.utils.Collections
import common.utils.MongoDBConfig
import domain.entries.UserEntity
import domain.repositories.EncryptionRepository
import domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import org.bson.conversions.Bson
import java.util.*
import kotlin.time.Duration.Companion.days

class UserRepositoryImpl(
    private val db:MongoDBConfig,
    private val jwtConfig:JWTConfig,
    private val encryption: EncryptionRepository
):UserRepository {

    private val userCollection = db.database.getCollection<UserEntity>(Collections.USER.name)
    private fun generateToken(userId: String, durationInMillis: Long): String {
        return JWT.create()
            .withAudience(jwtConfig.jwtAudience)
            .withIssuer(jwtConfig.jwtIssuer)
            .withClaim("id", userId)
            .withExpiresAt(Date(System.currentTimeMillis() + durationInMillis))
            .sign(Algorithm.HMAC256(jwtConfig.jwtSecret))
    }

    override suspend fun createUser(user: UserEntity): Resource<Any> = withContext(Dispatchers.IO) {
        val doesSimilarEmailExist = userCollection.find(Filters.eq(UserEntity::email.name, user.email)).firstOrNull()
        if (doesSimilarEmailExist != null) return@withContext Resource.Error(
            message = "User with similar email already exists"
        )
        val userCreationResult = userCollection.insertOne(user.copy(password = encryption.hashText(user.password)))
            .wasAcknowledged()
        if (!userCreationResult) return@withContext Resource.Error(message = "Failed to create user")

        return@withContext Resource.Success(
            message = "User created successfully",
            data = null
        )
    }

    override suspend fun signIn(email: String, password: String): Resource<Any>  = withContext(Dispatchers.IO){
        val user = userCollection.find(Filters.eq(UserEntity::email.name, email)).firstOrNull()
            ?: return@withContext Resource.Error(data = null, message = "Invalid email or password")

        if (!encryption.verifyHashCode(password, user.password)) {
            return@withContext Resource.Error(data = null, message = "Invalid email or password")
        }
        val token = generateToken(userId = user.id, durationInMillis = 3.days.inWholeMilliseconds)
        Resource.Success(
            message = "Sign in successful",
            data = mapOf(
                "token" to token
            )
        )

    }

    override suspend fun getUser(id: String): Resource<Any>  = withContext(Dispatchers.IO){
        val result = userCollection.find(Filters.eq(UserEntity::id.name, id)).firstOrNull()
            ?: return@withContext Resource.Error(message = "User not found")
        Resource.Success(
            message = "User found",
            data = result.toUser()
        )
    }

    override suspend fun updateUser(
        id: String,
        firstName: String?,
        middleName: String?,
        lastName: String?,
        email: String?,
    ): Resource<Any>  = withContext(Dispatchers.IO){

        val updates = mutableListOf<Bson>()

        firstName?.let { updates.add(Updates.set(UserEntity::firstName.name, it)) }
        middleName?.let { updates.add(Updates.set(UserEntity::firstName.name, it)) }
        lastName?.let { updates.add(Updates.set(UserEntity::lastName.name, it)) }
        email?.let { updates.add(Updates.set(UserEntity::email.name, it)) }

        userCollection.findOneAndUpdate(
            Filters.eq(UserEntity::id.name, id),
            Updates.combine(updates)
        ) ?: return@withContext Resource.Error(message = "User not found")

        return@withContext Resource.Success(data = null, message = "User updated successfully")

    }
}