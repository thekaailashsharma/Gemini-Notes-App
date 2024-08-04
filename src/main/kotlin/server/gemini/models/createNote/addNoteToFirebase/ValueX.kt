package server.gemini.models.createNote.addNoteToFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ValueX(
    @SerialName("mapValue")
    val mapValue: MapValue? = MapValue()
)