package domain.entries

import domain.models.User
import org.bson.types.ObjectId
import java.time.Instant

data class UserEntity(
    val id: String = ObjectId().toString(),
    val firstName: String,
    val middleName: String? = null,
    val lastName: String,
    val email: String,
    val password: String,
    val createdAt:String =  Instant.now().toString()
){
    fun toUser() = User(
        id = id,
        firstName = firstName,
        middleName = middleName,
        lastName = lastName,
        email = email,
        createdAt = createdAt
    )
}
