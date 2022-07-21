package com.alexis.shop.domain.model.product.modelbaru

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class ProductBaruModel(
    val barcode: String? = "",
    val change_to_listed: String? = "",
    val change_to_stored: String? ="",
    val color_id: String? ="",
    val item_code: String? ="",
    val material_id: String? ="",
    val name: String? ="",
    val price: Int? =0,
    val product_id: Int? =0,
    val product_image: @RawValue List<ImagesModel>? = null,
    val size_id: String? ="",
    val status: String? ="",
    val stock: Int? =0,
    val stock_keeping_unit: String? ="",
    val style_id: String? ="",
    val subcategory_id: Int? = 0,
    val weight: Int? =0
): Parcelable

//data class ImageProductModel(
//    val imageProduct : List<ImagesModel>? = null
//)
@Parcelize
data class ImagesModel(
    val bag_wishlist_order_display: Int,
    val image_url: String,
    val product_detail_display: Int,
    val product_image_id: Int,
    val product_item_code: String,
    val product_list_display: String,
    val type: String
) : Parcelable
