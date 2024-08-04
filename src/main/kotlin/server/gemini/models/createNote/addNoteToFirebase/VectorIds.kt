package server.gemini.models.createNote.addNoteToFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VectorIds(
    @SerialName("arrayValue")
    val arrayValue: ArrayValueX? = ArrayValueX()
)