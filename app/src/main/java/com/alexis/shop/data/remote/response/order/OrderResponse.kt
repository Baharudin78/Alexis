package com.alexis.shop.data.remote.response.order

data class OrderResponse(
    val code: Int,
    val `data`: OrderListItem,
    val error: Any,
    val status: String
)
data class OrderListItem(
    val items: List<OrderItem>
)
data class OrderItem(
    val address: AddressOrderItem,
    val address_id: Int,
    val customer_id: Int,
    val detail: List<OrderDetailItem>,
    val duration: Any,
    val final_price: Int,
    val id: Int,
    val price: Int,
    val resi_number: Any,
    val shipping_delay: Any,
    val status: Int,
    val transaction_code: String,
    val use_voucher: Any
)

data class AddressOrderItem(
    val address: String,
    val address_2: String,
    val as_dropship: Int,
    val customer_id: Int,
    val id: Int,
    val is_default: Int,
    val latitude: Any,
    val longitude: Any,
    val postal_code: Int,
    val recipient_name: String,
    val recipient_phone_number: String,
    val village_id: Int
)

data class OrderDetailItem(
    val final_price: Int,
    val id: Int,
    val price: Int,
    val product: ProductDetailOrderItem,
    val product_item_code: String,
    val transaction_order_id: Int
)

data class ProductDetailOrderItem(
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
    val product_material_id: Any,
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