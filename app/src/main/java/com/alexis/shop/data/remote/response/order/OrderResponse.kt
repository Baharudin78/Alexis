package com.alexis.shop.data.remote.response.order

import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: OrderListItem,
    @SerializedName("error")
    val error: Any,
    @SerializedName("status")
    val status: String
)
data class OrderListItem(
    @SerializedName("items")
    val items: List<OrderItem>
)
data class OrderItem(
    @SerializedName("address")
    val address: AddressOrderItem,
    @SerializedName("address_id")
    val address_id: Int,
    @SerializedName("customer_id")
    val customer_id: Int,
    @SerializedName("detail")
    val detail: List<OrderDetailItem>,
    @SerializedName("duration")
    val duration: Any,
    @SerializedName("final_price")
    val final_price: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("resi_number")
    val resi_number: Any,
    @SerializedName("shipping_delay")
    val shipping_delay: Any,
    @SerializedName("status")
    val status: Int,
    @SerializedName("transaction_code")
    val transaction_code: String,
    @SerializedName("use_voucher")
    val use_voucher: Any
)

data class AddressOrderItem(
    @SerializedName("address")
    val address: String,
    @SerializedName("address_2")
    val address_2: String,
    @SerializedName("as_dropship")
    val as_dropship: Int,
    @SerializedName("customer_id")
    val customer_id: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_default")
    val is_default: Int,
    @SerializedName("latitude")
    val latitude: Any,
    @SerializedName("longitude")
    val longitude: Any,
    @SerializedName("postal_code")
    val postal_code: Int,
    @SerializedName("recipient_name")
    val recipient_name: String,
    @SerializedName("recipient_phone_number")
    val recipient_phone_number: String,
    @SerializedName("village_id")
    val village_id: Int
)

data class OrderDetailItem(
    @SerializedName("final_price")
    val final_price: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("product")
    val product: ProductDetailOrderItem,
    @SerializedName("product_item_code")
    val product_item_code: String,
    @SerializedName("transaction_order_id")
    val transaction_order_id: Int
)

data class ProductDetailOrderItem(
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
    val product_material_id: Any,
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
    @SerializedName("user_id")
    val user_id: Int,
    @SerializedName("weight")
    val weight: Int
)