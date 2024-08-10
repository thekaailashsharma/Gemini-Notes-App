package server.gemini.models.createNote.addNoteToFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import server.gemini.models.signUpFirebase.SignUpFirebaseError

@Serializable
data class AddNoteFireBaseResponse(
    @SerialName("createTime")
    val createTime: String? = "",
    @SerialName("fields")
    val fields: Fields? = Fields(),
    @SerialName("name")
    val name: String? = "",
    @SerialName("updateTime")
    val updateTime: String? = "",
    @SerialName("error")
    val error: SignUpFirebaseError? = null
)

@Serializable
data class GetNotesResponse(
    @SerialName("documents")
    val documents: List<AddNoteFireBaseResponse?>? = listOf(),
)
