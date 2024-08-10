package server.gemini.models.searchNotes.addSearchToFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import server.gemini.models.signUpFirebase.SignUpFirebaseError

@Serializable
data class SearchNoteRequest(
    @SerialName("fields")
    val fields: Fields? = Fields()
)

@Serializable
data class SearchFireStoreResponse(
    @SerialName("createTime")
    val createTime: String? = null,
    @SerialName("fields")
    val fields: Fields? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("updateTime")
    val updateTime: String? = null,
    val error: SignUpFirebaseError? = null
)