package com.alexis.shop.domain.model.courier

data class CourierModel(
    val name : String? = null,
    val rateName : String? = null,
    val logoUri : String? = null,
    val finalRate : Int? = null,
    val description : String? = null
)
