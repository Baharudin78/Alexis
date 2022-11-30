package com.alexis.shop.data.remote.response.profil

import com.google.gson.annotations.SerializedName

data class ProfilResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: ProfilItem,
    @SerializedName("error")
    val error: Any,
    @SerializedName("meta")
    val meta: Any,
    @SerializedName("status")
    val status: String
)

data class ProfilItem(
    @SerializedName("item")
    val item: Profil
)

data class Profil(
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_blacklist")
    val is_blacklist: Int,
    @SerializedName("kode_referal")
    val kode_referal: Any,
    @SerializedName("nama_lengkap")
    val nama_lengkap: String,
    @SerializedName("no_telp")
    val no_telp: String,
    @SerializedName("registration_date")
    val registration_date: String,
    @SerializedName("tanggal_lahir")
    val tanggal_lahir: String,
    @SerializedName("total_poin")
    val total_poin: Int,
    @SerializedName("user_id")
    val user_id: Int
)