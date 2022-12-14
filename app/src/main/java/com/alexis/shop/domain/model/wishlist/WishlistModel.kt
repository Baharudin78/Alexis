package com.alexis.shop.domain.model.wishlist

import com.alexis.shop.data.remote.response.wishlist.ProductItem
import java.io.Serializable

data class WishlistModel(
    val id : Int? = null,
    val customerId : Int? = null,
    val productItemCode : String? = null,
    val product : ProductItem? = null,
) : Serializable

data class ProductItemModel(
    val id : Int? = null,
    val barcode : String? = null,
    val stock_keeping_unit : String? = null,
    val item_code : String? = null,
    val name : String? = null,
    val product_subcategory_id : Int? = null,
    val stock : Int? = null,
    val price : Int? = null,
    val weight : Int? = null,
    val style_code : String? = null,
    val product_material_id : String? = null,
    val color_code : String? = null,
    val product_size_id : String? = null,
    val packaging_id : Int? = null,
    val status : String? = null,
    val change_to_stored : String? = null,
    val change_to_listed : String? = null,
    val change_to_delisted : String? = null,
    val store_location_id : Int? = null,
    val user_id : Int? = null
)
