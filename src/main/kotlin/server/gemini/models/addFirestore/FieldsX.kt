package server.gemini.models.addFirestore


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FieldsX(
    @SerialName("email")
    val email: EmailX? = null,
    @SerialName("name")
    val name: NameX? = null,
    @SerialName("pass")
    val pass: PassX? = null,
    @SerialName("id")
    val iD: IDX? = null
)