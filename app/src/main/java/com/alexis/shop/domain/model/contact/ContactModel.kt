package com.alexis.shop.domain.model.contact


data class ContactModel(
    val items: List<ContactDataModel> = mutableListOf()
)
data class ContactDataModel(
    val email: String? = null,
    val id: Int? = null,
    val whatsapp: String? = null
)