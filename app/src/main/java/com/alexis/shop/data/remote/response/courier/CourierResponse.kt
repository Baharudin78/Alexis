package com.alexis.shop.data.remote.response.courier

import com.alexis.shop.domain.model.courier.CourierModel

data class CourierResponse(
    val code : Int? = null,
    val data : CourierData? = null,
    val error : String? = null,
    val status : String?
)

data class CourierData(
    val courier : CourierItem? = null
)

data class CourierItem(
    val name : String? = null,
    val rateName : String? = null,
    val logoUri : String? = null,
    val finalRate : Int? = null,
    val description : String? = null
)

fun CourierItem.toCourierModel() : CourierModel {
    return CourierModel(
        name, rateName, logoUri, finalRate, description
    )
}

