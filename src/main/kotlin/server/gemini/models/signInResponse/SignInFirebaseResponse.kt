package server.gemini.models.signInResponse


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import server.gemini.models.signUpFirebase.SignUpFirebaseError

@Serializable
data class SignInFirebaseResponse(
    @SerialName("displayName")
    val displayName: String? = null,
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
    @SerialName("registered")
    val registered: Boolean? = null,
    val error: SignUpFirebaseError? = null
)