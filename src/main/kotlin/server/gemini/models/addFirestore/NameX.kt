package server.gemini.models.addFirestore


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NameX(
    @SerialName("stringValue")
    val stringValue: String?
)