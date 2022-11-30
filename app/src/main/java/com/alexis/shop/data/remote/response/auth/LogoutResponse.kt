package com.alexis.shop.data.remote.response.auth

import com.google.gson.annotations.SerializedName

data class LogoutResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("error")
    val error: Any,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)