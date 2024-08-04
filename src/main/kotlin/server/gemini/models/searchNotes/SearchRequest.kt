package server.gemini.models.searchNotes


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchRequest(
    @SerialName("content")
    val content: String? = null,
    @SerialName("namespace")
    val namespace: String? = null
)