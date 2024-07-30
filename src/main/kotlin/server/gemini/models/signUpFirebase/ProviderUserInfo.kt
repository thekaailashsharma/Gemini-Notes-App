package server.gemini.models.signUpFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProviderUserInfo(
    @SerialName("email")
    val email: String?,
    @SerialName("federatedId")
    val federatedId: String?,
    @SerialName("providerId")
    val providerId: String?,
    @SerialName("rawId")
    val rawId: String?
)