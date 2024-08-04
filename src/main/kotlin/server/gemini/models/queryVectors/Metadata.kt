package server.gemini.models.queryVectors


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Metadata(
    @SerialName("genre")
    val genre: String? = null
)