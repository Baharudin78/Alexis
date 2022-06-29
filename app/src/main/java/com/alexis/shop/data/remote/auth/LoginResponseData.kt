package com.alexis.shop.data.remote.auth

import com.google.gson.annotations.SerializedName

data class LoginResponseData(
    var id: Int?,
    @SerializedName("user_id")
    var userId: Int?,
    @SerializedName("nama_lengkap")
    var fullName: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("no_telp")
    var phone: String?,
    @SerializedName("tanggal_lahir")
    var birthDate: String?
)