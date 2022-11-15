package com.alexis.shop.domain.model.address


data class AddressListModel(
    val address : List<AddressItemModel> = mutableListOf()
)
data class AddressItemModel(
    val id : Int? = null,
    val customerId : Int? = null,
    val recipientName : String? = null,
    val address : String? = null,
    val addressTwo : String? = null,
    val villageId : String? = null,
    val latitude : String? = null,
    val longitude : String? = null,
    val postalCode : String? = null,
    val recipientPhoneNumber : String? = null,
    val isDefault : Int? = null,
    val asDropship : Int? = null
)