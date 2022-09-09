package com.alexis.shop.domain.model.auth

data class RegisterModel(
    var fullName: String,
    var email: String,
    var phone: String,
    var password : String,
    var confirmPassword : String,
    var tanggal : String
)
