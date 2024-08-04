package server.gemini.models.queryVectors


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Usage(
    @SerialName("readUnits")
    val readUnits: Int? = null
)