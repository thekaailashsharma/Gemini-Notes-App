package server.gemini.models.createNote.addNoteToFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddNoteFireBaseResponse(
    @SerialName("createTime")
    val createTime: String? = "",
    @SerialName("fields")
    val fields: FieldsX? = FieldsX(),
    @SerialName("name")
    val name: String? = "",
    @SerialName("updateTime")
    val updateTime: String? = ""
)