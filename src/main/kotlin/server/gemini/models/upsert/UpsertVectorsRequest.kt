package server.gemini.models.upsert


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpsertVectorsRequest(
    @SerialName("vectors")
    val vectors: List<Vector?>? = null
)