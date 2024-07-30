package server.gemini.models

import kotlinx.serialization.Serializable
import server.gemini.models.signUpFirebase.SignUpFirebaseError

@Serializable
data class UserRegistrationResponse(
    val success: Boolean = false,
    val message: String = "",
    val error: SignUpFirebaseError? = null
)