package com.alexis.shop.domain.model.contact


data class ContactModel(
    val items: List<ContactDataModel> = mutableListOf()
)
data class ContactDataModel(
    val email: String,
    val id: Int,
    val whatsapp: String
)