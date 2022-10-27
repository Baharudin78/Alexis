package com.alexis.shop.domain.model.store_location

import java.io.Serializable

data class StoreLocationByNameModel(
    val province: String = "",
    val updatedAt: String = "",
    val city: String = "",
    val latitude: String = "",
    val name: String = "",
    val openTime: String = "",
    val createdAt: String = "",
    val phoneNumber: String = "",
    val id: Int = 0,
    val closeTime: String = "",
    val longitude: String = ""
): Serializable
