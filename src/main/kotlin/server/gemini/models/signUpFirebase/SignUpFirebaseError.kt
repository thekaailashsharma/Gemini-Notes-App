package server.gemini.models.signUpFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpFirebaseError(
    @SerialName("error")
    val error: Error?
)