package com.alexis.shop.data.remote.response.shoppingbag

data class ShopingBagNewResponse(
    val code: Int,
    val `data`: ShopingBagList,
    val error: Any,
    val status: String
)

data class ShopingBagList(
    val items: List<ShopingBagItem>
)

data class ShopingBagItem(
    val customer_id: Int,
    val final_price: Int,
    val id: Int,
    val price: Int,
    val product: ShopingProduct,
    val product_item_code: String,
    val product_size_id: Int,
    val unit: Int
)

data class ShopingProduct(
    val barcode: String,
//    val change_to_delisted: Any,
//    val change_to_listed: Any,
//    val change_to_stored: Any,
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
