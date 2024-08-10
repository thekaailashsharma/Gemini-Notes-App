package server.gemini.models.searchNotes.error


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FireBaseError(
    @SerialName("error")
    val error: Error? = null
)