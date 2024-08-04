package server.gemini.models.createNote.addNoteToFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Timestamp(
    @SerialName("integerValue")
    val integerValue: String? = null
)