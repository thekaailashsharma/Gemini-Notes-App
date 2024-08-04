package server.gemini.models.createNote.addNoteToFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArrayValueXX(
    @SerialName("values")
    val values: List<ValueX>? = listOf()
)