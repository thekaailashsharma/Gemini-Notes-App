package server.gemini.models.createNote.addNoteToFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArrayValueX(
    @SerialName("values")
    val values: List<Value>? = listOf()
)