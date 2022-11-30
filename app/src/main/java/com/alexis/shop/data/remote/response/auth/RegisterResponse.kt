package com.alexis.shop.data.remote.response.auth

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("data")
    var data: Items,
    @SerializedName("code")
    var code : Int,
    @SerializedName("status")
    val status : String
)
data class Items(
    @SerializedName("item")
    val user : RegisterResponseData
)