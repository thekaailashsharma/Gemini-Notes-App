package server.gemini.models.createNote.addNoteToFirebase


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FieldsXX(
    @SerialName("checkInDate")
    val checkInDate: CheckInDate? = null,
    @SerialName("checkInTime")
    val checkInTime: CheckInTime? = null,
    @SerialName("checkOutDate")
    val checkOutDate: CheckOutDate? = null,
    @SerialName("checkOutTime")
    val checkOutTime: CheckOutTime? = null,
    @SerialName("confirmation")
    val confirmation: Confirmation? = null,
    @SerialName("hotel")
    val hotel: Hotel? = null,
    @SerialName("notes")
    val notes: Notes? = null,
    @SerialName("paid")
    val paid: Paid? = null,
    @SerialName("phoneNumber")
    val phoneNumber: PhoneNumber? = null,
    @SerialName("roomType")
    val roomType: RoomType? = null
)