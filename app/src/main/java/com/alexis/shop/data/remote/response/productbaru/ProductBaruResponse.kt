package com.alexis.shop.data.remote.response.productbaru

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ProductBaruResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: Data? = null,
    @SerializedName("error")
    val error: Any,
    @SerializedName("status")
    val status: String
)
data class Data(
    @SerializedName("items")
    val product : List<ProductItems>? = null
)
data class ProductItems(
    @SerializedName("barcode")
    val barcode: String?,
    @SerializedName("change_to_listed")
    val change_to_listed: String?,
    @SerializedName("change_to_stored")
    val change_to_stored: String?,
    @SerializedName("color_id")
    val color_id: String?,
    @SerializedName("item_code")
    val item_code: String?,
    @SerializedName("product_material_id")
    val product_material_id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("product_image")
    val product_image: List<ImageModel>? = null,
    @SerializedName("product_size_id")
    val product_size_id: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("stock")
    val stock: Int?,
    @SerializedName("stock_keeping_unit")
    val stock_keeping_unit: String?,
    @SerializedName("style_id")
    val style_id: String?,
    @SerializedName("subcategory_id")
    val subcategory_id: Int?,
    @SerializedName("weight")
    val weight: Int?
)
//data class ImageProduct(
//    @field:SerializedName("product_image")
//    val imageProduct : List<ImageModel>? = null
//)
@Parcelize
data class ImageModel(
    @SerializedName("bag_wishlist_order_display")
    val bag_wishlist_order_display: Int,
    @SerializedName("image_url")
    val image_url: String,
    @SerializedName("product_detail_display")
    val product_detail_display: Int,
    @SerializedName("product_image_id")
    val product_image_id: Int,
    @SerializedName("product_item_code")
    val product_item_code: String,
    @SerializedName("product_list_display")
    val product_list_display: String,
    @SerializedName("type")
    val type: String
) : Parcelable