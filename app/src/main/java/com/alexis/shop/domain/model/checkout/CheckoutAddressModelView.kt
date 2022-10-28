package com.alexis.shop.domain.model.checkout

data class CheckoutAddressModelView(
    val recipientName: String = "",
    val address: String = "",
    val addressTwo: String = "",
    val villageId: String = "",
    val postalCode: String = "",
    val recipientPhoneNumber: String = "",
    val asDropship: String = "",
    val isDefault: String = "",
    val latitude: String = "",
    val longitude: String = "",
)
