package presentation.services

import domain.usecase.user.CreateUserUseCase
import domain.usecase.user.GetUserProfileUserCase
import domain.usecase.user.SignInUserUseCase
import presentation.dtos.requests.CreateUserReq
import presentation.dtos.requests.SignInReq

class UserService(
    private val createUserUseCase: CreateUserUseCase,
    private val signInUserUseCase: SignInUserUseCase,
    private val getUserProfileUserCase: GetUserProfileUserCase
) {
    suspend fun createUser(user:CreateUserReq) = createUserUseCase(user= user.toUserEntity())
    suspend fun signIn(credentials:SignInReq) = signInUserUseCase(
        email = credentials.email,
        password = credentials.password
    )
    suspend fun getUserProfile(id:String) = getUserProfileUserCase(id)
}