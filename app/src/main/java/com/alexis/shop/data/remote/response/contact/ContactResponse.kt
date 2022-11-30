package com.alexis.shop.data.remote.response.contact

import com.google.gson.annotations.SerializedName

data class ContactResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: ContactItems,
    @SerializedName("error")
    val error: Any,
    @SerializedName("status")
    val status: String
)
data class ContactItems(
    @SerializedName("item")
    val item: ContactData
)
data class ContactData(
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("whatsapp")
    val whatsapp: String
)