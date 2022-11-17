package com.alexis.shop.domain.model.order

data class OrderListModel(
    var order : List<OrderItemModel> = mutableListOf()
)

data class OrderItemModel(
    val address: AddressItemModel,
    val address_id: Int,
    val customer_id: Int,
    val detail: List<OrderDetailModel>,
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

data class OrderDetailModel(
    val final_price: Int,
    val id: Int,
    val price: Int,
    val product: ProductDetailModel,
    val product_item_code: String,
    val transaction_order_id: Int
)

data class AddressItemModel(
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

data class ProductDetailModel(
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