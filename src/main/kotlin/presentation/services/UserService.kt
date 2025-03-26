package presentation.services

import domain.usecase.user.CreateUserUseCase
import domain.usecase.user.GetUserProfileUserCase
import domain.usecase.user.SignInUserUseCase
import domain.usecase.user.UpdateUserUseCase
import presentation.dtos.requests.CreateUserReq
import presentation.dtos.requests.SignInReq
import presentation.dtos.requests.UpdateUserReq

class UserService(
    private val createUserUseCase: CreateUserUseCase,
    private val signInUserUseCase: SignInUserUseCase,
    private val getUserProfileUserCase: GetUserProfileUserCase,
    private val updateUserUseCase: UpdateUserUseCase
) {
    suspend fun createUser(user:CreateUserReq) = createUserUseCase(user= user.toUserEntity())
    suspend fun signIn(credentials:SignInReq) = signInUserUseCase(
        email = credentials.email,
        password = credentials.password
    )
    suspend fun getUserProfile(id:String) = getUserProfileUserCase(id)
    suspend fun updateUser(userId:String, body:UpdateUserReq) = updateUserUseCase(
        id = userId,
        firstName = body.firstName,
        middleName = body.middleName,
        lastName = body.lastName,
        email = body.email
    )
}