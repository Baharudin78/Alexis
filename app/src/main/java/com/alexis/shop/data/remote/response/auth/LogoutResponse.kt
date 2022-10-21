package com.alexis.shop.data.remote.response.auth

data class LogoutResponse(
    val code: Int,
    val error: Any,
    val message: String,
    val status: String
)