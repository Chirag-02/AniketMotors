package com.aniketassociates.model.aniketMotorsModel

import androidx.room.Entity
import androidx.room.PrimaryKey

data class AniketMotorsListResponse(
    val data: List<Data>,
    val message: String,
    val session: Session,
    val success: Boolean
){
    @Entity(tableName = "result")
    data class Data(
        @PrimaryKey
        val entry_id: String,
        val registration_no: String,
        val regCity:String,
        val regNo:String
    )
    data class Session(
        val LoginToken: String,
        val expires: Boolean,
        val is_LoggedIn: Boolean
    )
}