package com.alexis.shop.domain.model.product

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageModel(
    var image: String,
    var scaled: Boolean
): Parcelable

