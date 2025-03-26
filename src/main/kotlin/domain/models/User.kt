package domain.models


data class User(
    val id: String,
    val firstName: String,
    val middleName: String? = null,
    val lastName: String,
    val email: String,
    val createdAt:String,
)