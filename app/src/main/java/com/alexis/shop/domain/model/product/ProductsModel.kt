package com.alexis.shop.domain.model.product

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductsModel(
    var thumbnail: String = "",
    var updatedAt: String = "",
    var price: Int = 0,
    var createdAt: String = "",
    var id: Int = 0,
    var indonesiaName: String = "",
    var stock: Int = 0,
    var barcode: String = "",
    var englishName: String = "",
    var imageType: String = ""
): Parcelable
