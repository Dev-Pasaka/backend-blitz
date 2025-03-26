package domain.usecase.user

import common.Resource
import common.utils.Type
import common.utils.logger
import domain.repositories.UserRepository
import io.ktor.http.*
import presentation.dtos.DefaultResponse

class UpdateUserUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(
        id: String,
        firstName:String? = null,
        middleName:String? = null,
        lastName:String? = null,
        email:String? = null,
    ) = try {
        when(val result = repository.updateUser(id, firstName, middleName, lastName, email)) {
            is Resource.Error -> DefaultResponse(
                httpStatusCode = 400,
                status = false,
                message = result.message ?: "Something went wrong",
                data = result.data,
            )
            is Resource.Success -> DefaultResponse(
                httpStatusCode = 200,
                status = true,
                message = result.message ?: "Success",
                data = result.data,
            )
        }
    }catch (e:Exception){
        e.message?.logger(Type.WARN) ?: e.printStackTrace()
        DefaultResponse(
            httpStatusCode = HttpStatusCode.InternalServerError.value,
            status = false,
            message = e.message ?: "Something went wrong",
        )
    }
}