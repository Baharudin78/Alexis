package com.alexis.shop.domain.model.store_location

import java.io.Serializable

data class StoreLocationByNameModel(
    val province: String = "",
    val updatedAt: String = "",
    val city: String = "",
    val latitude: Int = 0,
    val name: String = "",
    val openTime: String = "",
    val createdAt: String = "",
    val phoneNumber: String = "",
    val id: Int = 0,
    val closeTime: String = "",
    val longitude: Int = 0
): Serializable
