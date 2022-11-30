package com.alexis.shop.data.remote.response.shoppingbag

import com.google.gson.annotations.SerializedName

data class ShopingBagNewResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: ShopingBagList,
    @SerializedName("error")
    val error: Any,
    @SerializedName("status")
    val status: String
)

data class ShopingBagList(
    @SerializedName("items")
    val items: List<ShopingBagItem>
)

data class ShopingBagItem(
    @SerializedName("customer_id")
    val customer_id: Int,
    @SerializedName("final_price")
    val final_price: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("product")
    val product: ShopingProduct,
    @SerializedName("product_item_code")
    val product_item_code: String,
    @SerializedName("product_size_id")
    val product_size_id: Int,
    @SerializedName("unit")
    val unit: Int
)

data class ShopingProduct(
    @SerializedName("barcode")
    val barcode: String,
    @SerializedName("color_code")
    val color_code: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("item_code")
    val item_code: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("packaging_id")
    val packaging_id: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("product_material_id")
    val product_material_id: String,
    @SerializedName("product_size_id")
    val product_size_id: String,
    @SerializedName("product_subcategory_id")
    val product_subcategory_id: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("stock")
    val stock: Int,
    @SerializedName("stock_keeping_unit")
    val stock_keeping_unit: Any,
    @SerializedName("store_location_id")
    val store_location_id: Int,
    @SerializedName("style_code")
    val style_code: String,
    @SerializedName("user")
    val user_id: Int,
    @SerializedName("weight")
    val weight: Int
)
