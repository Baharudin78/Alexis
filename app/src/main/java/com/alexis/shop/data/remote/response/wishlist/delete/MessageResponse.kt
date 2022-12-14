package com.alexis.shop.data.remote.response.wishlist.delete

data class MessageResponse(
    val code: Int,
    val `data`: Data,
    val error: Any,
    val message: String,
    val meta: Any,
    val status: String
)