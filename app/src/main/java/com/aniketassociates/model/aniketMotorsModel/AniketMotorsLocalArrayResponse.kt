package com.aniketassociates.model.aniketMotorsModel

data class AniketMotorsLocalArrayResponse(
    val `data`: List<Data>
){

    data class Data(
        val entry_id: String,
        val registration_no: String
    )
}