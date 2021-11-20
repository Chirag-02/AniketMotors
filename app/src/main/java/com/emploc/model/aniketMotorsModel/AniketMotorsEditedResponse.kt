package com.emploc.model.aniketMotorsModel

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

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