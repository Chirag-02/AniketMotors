package com.aniketassociates.model.aniketMotorsModel

data class AniketMotorsEditedResponse(
    val data: List<Data>,
    val message: String,
    val session: Session,
    val success: Boolean
){
    data class Data(

        val entry_id: String,
        val registration_no: String
    )
    data class Session(
        val LoginToken: String,
        val expires: Boolean,
        val is_LoggedIn: Boolean
    )
}