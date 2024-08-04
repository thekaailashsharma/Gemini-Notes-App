package server.gemini.models.searchNotes


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchNotesResponse(
    @SerialName("content")
    val content: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("response")
    val response: String? = null
)