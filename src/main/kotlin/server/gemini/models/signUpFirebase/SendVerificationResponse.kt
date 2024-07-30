package server.gemini.models.signUpFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SendVerificationResponse(
    @SerialName("email")
    val email: String?,
    @SerialName("kind")
    val kind: String?,
    @SerialName("idToken")
    val idToken: String? = null,
    @SerialName("userId")
    val userId: String? = null,

)