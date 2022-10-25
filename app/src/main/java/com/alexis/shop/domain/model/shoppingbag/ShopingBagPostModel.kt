package com.alexis.shop.domain.model.shoppingbag

import com.google.gson.annotations.SerializedName

data class ShopingBagPostModel(
    val id : Int? = null,
    val customerId : Int? = null,
    val productItemCode : String? = null,
    val productSizeId : String? = null,
    val unit : String? = null,
    val price : Int? = null,
    val finalPrice : Int? = null
)