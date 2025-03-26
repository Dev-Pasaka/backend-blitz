package presentation.dtos

import com.google.gson.Gson
import io.ktor.http.*


data class DefaultResponse<T>(
    val httpStatusCode: Int = HttpStatusCode.OK.value,
    val status:Boolean = false,
    val message:String = "",
    val data:T? = null
){
    fun toJson() = Gson().toJson(this)

}