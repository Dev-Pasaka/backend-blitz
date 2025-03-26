package domain.usecase.user

import common.Resource
import common.utils.Type
import common.utils.logger
import domain.repositories.UserRepository
import io.ktor.http.*
import presentation.dtos.DefaultResponse

class SignInUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String,password:String):DefaultResponse<Any> = try{
        when(val result = userRepository.signIn(email,password)){
            is Resource.Error -> DefaultResponse(
                httpStatusCode = HttpStatusCode.OK.value,
                status = false,
                message = result.message ?: "Something went wrong"
            )
            is Resource.Success -> DefaultResponse(
                httpStatusCode = HttpStatusCode.OK.value,
                status = true,
                message = result.message ?: "Sign In Success",
                data = result.data
            )
        }
    }catch (e:Exception){
        e.message?.logger(Type.WARN) ?: e.printStackTrace()
        DefaultResponse(
            httpStatusCode = HttpStatusCode.InternalServerError.value,
            status = false,
            message = e.message ?: "Something went wrong"
        )
    }

}