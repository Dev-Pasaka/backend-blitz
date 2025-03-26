package presentation.dtos.requests

data class UpdateUserReq(
    val firstName: String? = null,
    val middleName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
)
