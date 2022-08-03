package com.alexis.shop.data.remote.auth

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("data")
    var data: Items,
    var code : Int,
    val status : String
)
data class Items(
    @SerializedName("item")
    val user : RegisterResponseData
)