package server.gemini.models.addFirestore

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PassX(
    @SerialName("stringValue")
    val stringValue: String?
)

@Serializable
data class IDX(
    @SerialName("stringValue")
    val stringValue: String?
)