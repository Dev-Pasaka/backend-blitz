package domain.repositories

import common.Resource
import domain.entries.UserEntity

interface UserRepository {
    suspend fun createUser(user:UserEntity):Resource<Any>
    suspend fun signIn(
        email: String,
        password: String,
    ): Resource<Any>
    suspend fun getUser(id: String): Resource<Any>
    suspend fun updateUser(
        id: String,
        firstName:String?,
        middleName:String?,
        lastName:String?,
        email:String?,
    ): Resource<Any>
}