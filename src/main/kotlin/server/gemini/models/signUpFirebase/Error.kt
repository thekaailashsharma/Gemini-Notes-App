package server.gemini.models.signUpFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Error(
    @SerialName("code")
    val code: Int? = null,
    @SerialName("errors")
    val errors: List<ErrorX>? = null,
    @SerialName("message")
    val message: String? = null
)