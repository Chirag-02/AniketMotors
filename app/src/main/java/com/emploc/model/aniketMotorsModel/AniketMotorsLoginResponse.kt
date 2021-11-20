package com.emploc.model.aniketMotorsModel

data class AniketMotorsLoginResponse(
    val `data`: Data,
    val message: String,
    val success: Boolean
) {
    data class Data(
        val adharcardno: String,
        val fileuploaded: String,
        val id: String,
        val parentid: String,
        val phoneno: String,
        val status: String,
        val username: String
    )
}