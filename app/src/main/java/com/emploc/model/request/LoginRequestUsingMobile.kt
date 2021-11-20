package com.emploc.model.request


import com.google.gson.annotations.SerializedName

data class LoginRequestUsingMobile(
    @SerializedName("mobile")
    val mobile: String
)