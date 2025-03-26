package common

import presentation.dtos.DefaultResponse

sealed class Resource<T : Any>(
    val data:T? = null,
    val message:String? = null,
){
    class Success<T : Any>(data: T?, message: String):Resource<T>(data = data, message)
    class Error<T : Any>(message: String, data: T? = null):Resource<T>(message = message,data = data)

    fun toDefaultResponse(): DefaultResponse<Any> {
        return when(this){
            is Success -> DefaultResponse(
                httpStatusCode = 200,
                status = true,
                message = message ?: "Success",
                data = data,
            )
            is Error -> DefaultResponse(
                httpStatusCode = 400,
                status = false,
                message = message ?: "Something went wrong",
                data = data,
            )
        }
    }
}