package domain.usecase.user

import common.Resource
import common.utils.Type
import common.utils.logger
import domain.entries.UserEntity
import domain.repositories.UserRepository
import presentation.dtos.DefaultResponse

class CreateUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: UserEntity):DefaultResponse<Any> = try {
        when(val result =userRepository.createUser(user)){
            is Resource.Success -> DefaultResponse(
                status = true,
                message = result.message ?: "Success",
                data = result.data
            )
            is Resource.Error -> DefaultResponse(
                status = false,
                message = result.message ?: "Error",
                data = result.data
            )
        }
    }catch (e:Exception){
        e.message?.logger(Type.INFO) ?: e.printStackTrace()
        DefaultResponse(
            status = false,
            message = e.message ?: "Error",
            data = null
        )
    }
}