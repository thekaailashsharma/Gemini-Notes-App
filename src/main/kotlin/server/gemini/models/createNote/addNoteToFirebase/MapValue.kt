package server.gemini.models.createNote.addNoteToFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MapValue(
    @SerialName("fields")
    val fields: FieldsXX? = FieldsXX()
)