package server.gemini.models.signUpFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import server.gemini.models.signUpFirebase.SignUpFirebaseError
import server.gemini.models.signUpFirebase.User

@Serializable
data class VerifyVerificationResponse(
    @SerialName("kind")
    val kind: String? = null,
    @SerialName("users")
    val users: List<User?>? = null,
    val error: SignUpFirebaseError? = null
)