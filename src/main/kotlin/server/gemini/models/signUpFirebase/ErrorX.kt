package server.gemini.models.signUpFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorX(
    @SerialName("domain")
    val domain: String? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("reason")
    val reason: String? = null
)