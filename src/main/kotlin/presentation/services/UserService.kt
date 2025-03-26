package presentation.services

import domain.entries.UserEntity
import domain.usecase.user.CreateUserUseCase
import presentation.dtos.requests.CreateUserReq

class UserService(
    private val createUserUseCase: CreateUserUseCase,
) {
    suspend fun createUser(user:CreateUserReq) = createUserUseCase(user= user.toUserEntity())
}