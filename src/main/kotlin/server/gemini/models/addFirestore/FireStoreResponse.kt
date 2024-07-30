package server.gemini.models.addFirestore


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import server.gemini.models.signUpFirebase.SignUpFirebaseError

@Serializable
data class FireStoreResponse(
    @SerialName("createTime")
    val createTime: String? = null,
    @SerialName("fields")
    val fields: FieldsX? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("updateTime")
    val updateTime: String? = null,
    val error: SignUpFirebaseError? = null
)