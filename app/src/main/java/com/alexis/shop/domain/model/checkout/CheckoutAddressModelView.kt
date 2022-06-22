package com.alexis.shop.domain.model.checkout

data class CheckoutAddressModelView(
    val address: String = "",
    val city: String = "",
    val asDropship: Boolean = false,
    val isDefault: Boolean = false,
    val typeAddress: String = "",
    val recipientPhoneNumber: String = "",
    val checkoutAddressId: Int = 0,
    val recipientName: String = "",
    val postalCode: Int = 0,
    val otherDetail: String = ""
)
