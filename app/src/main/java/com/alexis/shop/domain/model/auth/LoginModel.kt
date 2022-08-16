package com.alexis.shop.domain.model.auth

data class LoginModel(
    var id: Int,
    var userId: Int,
    var fullName: String,
    var email: String,
    var phone: String,
    var birthDate: String,
    var token : String
)
