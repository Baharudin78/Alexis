package com.alexis.shop.data.remote.response.contact

data class ContactResponse(
    val code: Int,
    val `data`: ContactItems,
    val error: Any,
    val status: String
)
data class ContactItems(
    val items: List<ContactData>
)
data class ContactData(
    val email: String,
    val id: Int,
    val whatsapp: String
)