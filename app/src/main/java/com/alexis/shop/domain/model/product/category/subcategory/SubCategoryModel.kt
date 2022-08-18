package com.alexis.shop.domain.model.product.category.subcategory

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SubCategoryModel(
    val merchandiseName : String,
    val id : Int
) : Parcelable