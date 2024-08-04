package server.gemini.models.createNote.addNoteToFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddNoteFirebaseRequest(
    @SerialName("fields")
    val fields: Fields? = Fields()
)