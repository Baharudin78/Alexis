package com.alexis.shop.data.remote.response.auth

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("data")
    var data: Data,
    var code : Int,
    val status : String
)
 data class Data(
     @SerializedName("item")
     val user : LoginResponseData
 )
