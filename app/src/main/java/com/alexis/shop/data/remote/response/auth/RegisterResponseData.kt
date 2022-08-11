package com.alexis.shop.data.remote.response.auth

import com.google.gson.annotations.SerializedName

data class RegisterResponseData(
    @SerializedName("nama_lengkap")
    var fullName: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("no_telp")
    var phone: String?
)