package server.gemini.models.searchNotes.error


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Error(
    @SerialName("code")
    val code: Int? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("status")
    val status: String? = null
)