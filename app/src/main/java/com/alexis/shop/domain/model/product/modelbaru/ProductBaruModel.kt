package com.alexis.shop.domain.model.product.modelbaru

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

data class AllProductBaruModel(
    val data : List<ProductBaruModel> = mutableListOf()
)

@Parcelize
data class ProductBaruModel(
    val id: Int,
    val barcode: String,
    val change_to_listed: String,
    val change_to_stored: String,
    val color_id: String,
    val item_code: String,
    val material_id: String,
    val name: String,
    val price: Int,
    val product_image: @RawValue List<ImagesModel>? = null,
    val product_size_id: String,
    val status: String,
    val stock: Int,
    val stock_keeping_unit: String,
    val style_id: String,
    val subcategory_id: Int,
    val weight: Int
): Parcelable

@Parcelize
data class ImagesModel(
    val bag_wishlist_order_display: Int? = null,
    val image_url: String? = null,
    val product_detail_display: Int?= null,
    val product_image_id: Int?= null,
    val product_item_code: String?= null,
    val product_list_display: String?= null,
    val type: String?= null
) : Parcelable
