package com.alexis.shop.domain.model.shoppingbag

data class ShopingBagListModel(
    var bag : List<ShoppingBagModel> = mutableListOf()
)

data class ShoppingBagModel(
    val customer_id: Int,
    val final_price: Int,
    val id: Int,
    var price: Int,
    val product: ShopingProductModel?,
    val product_item_code: String,
    val product_size_id: Int,
    val unit: Int
)

data class ShopingProductModel(
    val barcode: String,
    val color_code: String,
    val id: Int,
    val item_code: String,
    val name: String,
    val packaging_id: Int,
    val price: Int,
    val product_material_id: String,
    val product_size_id: String,
    val product_subcategory_id: Int,
    val status: String,
    val stock: Int,
    val stock_keeping_unit: Any,
    val store_location_id: Int,
    val style_code: String,
    val user_id: Int,
    val weight: Int
)