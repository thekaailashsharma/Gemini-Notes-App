package server.gemini.models.upsert


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpsertVectorsResponse(
    @SerialName("upsertedCount")
    var upsertedCount: Int? = null
)