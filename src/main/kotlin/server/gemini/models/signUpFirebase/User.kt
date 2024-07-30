package server.gemini.models.signUpFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import server.gemini.models.signUpFirebase.ProviderUserInfo

@Serializable
data class User(
    @SerialName("createdAt")
    val createdAt: String?,
    @SerialName("email")
    val email: String?,
    @SerialName("emailVerified")
    val emailVerified: Boolean?,
    @SerialName("lastLoginAt")
    val lastLoginAt: String?,
    @SerialName("lastRefreshAt")
    val lastRefreshAt: String?,
    @SerialName("localId")
    val localId: String?,
    @SerialName("passwordHash")
    val passwordHash: String?,
    @SerialName("passwordUpdatedAt")
    val passwordUpdatedAt: Long?,
    @SerialName("providerUserInfo")
    val providerUserInfo: List<ProviderUserInfo?>?,
    @SerialName("validSince")
    val validSince: String?
)