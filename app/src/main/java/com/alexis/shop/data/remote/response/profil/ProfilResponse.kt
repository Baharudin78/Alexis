package com.alexis.shop.data.remote.response.profil

data class ProfilResponse(
    val code: Int,
    val `data`: ProfilItem,
    val error: Any,
    val meta: Any,
    val status: String
)

data class ProfilItem(
    val item: Profil
)

data class Profil(
    val email: String,
    val id: Int,
    val is_blacklist: Int,
    val kode_referal: Any,
    val nama_lengkap: String,
    val no_telp: String,
    val registration_date: String,
    val tanggal_lahir: String,
    val total_poin: Int,
    val user_id: Int
)