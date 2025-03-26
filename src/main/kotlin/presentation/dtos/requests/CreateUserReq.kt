package presentation.dtos.requests

import domain.entries.UserEntity

data class CreateUserReq(
    val firstName:String,
    val middleName:String? = null,
    val lastName:String,
    val email:String,
    val password:String,
){
    fun toUserEntity() = UserEntity(
        firstName = firstName,
        middleName = middleName,
        lastName = lastName,
        email = email,
        password = password
    )
}
