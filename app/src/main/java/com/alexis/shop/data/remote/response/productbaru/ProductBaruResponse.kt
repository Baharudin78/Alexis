package com.alexis.shop.data.remote.response.productbaru

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ProductBaruResponse(
    val code: Int,
    @field:SerializedName("data")
    val data: Data? = null,
    val error: Any,
    val status: String
)
data class Data(
    @field:SerializedName("items")
    val product : List<ProductItems>? = null
)
data class ProductItems(
    val barcode: String,
    val change_to_listed: String,
    val change_to_stored: String,
    val color_id: String,
    val item_code: String,
    val material_id: String,
    val name: String,
    val price: Int,
    val product_id: Int,
    val product_image: List<ImageModel>? = null,
    val size_id: String,
    val status: String,
    val stock: Int,
    val stock_keeping_unit: String,
    val style_id: String,
    val subcategory_id: Int,
    val weight: Int
)
//data class ImageProduct(
//    @field:SerializedName("product_image")
//    val imageProduct : List<ImageModel>? = null
//)
@Parcelize
data class ImageModel(
    val bag_wishlist_order_display: Int,
    val image_url: String,
    val product_detail_display: Int,
    val product_image_id: Int,
    val product_item_code: String,
    val product_list_display: String,
    val type: String
) : Parcelable