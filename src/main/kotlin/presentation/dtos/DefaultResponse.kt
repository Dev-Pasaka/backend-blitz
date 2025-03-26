package presentation.dtos

import io.ktor.http.*


data class DefaultResponse<T>(
    val httpStatusCode: Int = HttpStatusCode.OK.value,
    val status:Boolean = false,
    val message:String = "",
    val data:T? = null
)