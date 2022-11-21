package com.alexis.shop.domain.model.profil

data class ProfilModel(
    val email: String? = null,
    val id: Int? = null,
    val is_blacklist: Int? = null,
    val kode_referal: Any? = null,
    val nama_lengkap: String? = null,
    val no_telp: String? = null,
    val registration_date: String? = null,
    val tanggal_lahir: String? = null,
    val total_poin: Int? = null,
    val user_id: Int? = null
)
