package server.gemini.models.signUpFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import server.gemini.models.signUpFirebase.SignUpFirebaseError

@Serializable
data class SignUpFirebaseResponse(
    @SerialName("email")
    val email: String? = null,
    @SerialName("expiresIn")
    val expiresIn: String? = null,
    @SerialName("idToken")
    val idToken: String? = null,
    @SerialName("kind")
    val kind: String? = null,
    @SerialName("localId")
    val localId: String? = null,
    @SerialName("refreshToken")
    val refreshToken: String? = null,
    val error: SignUpFirebaseError? = null
)