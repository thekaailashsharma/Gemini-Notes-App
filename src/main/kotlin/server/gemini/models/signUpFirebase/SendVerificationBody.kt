package server.gemini.models.signUpFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SendVerificationBody(
    @SerialName("idToken")
    val idToken: String? = null,
    @SerialName("requestType")
    val requestType: String? = null
)

@Serializable
data class VerifyVerificationBody(
    @SerialName("idToken")
    val idToken: String? = null,
)