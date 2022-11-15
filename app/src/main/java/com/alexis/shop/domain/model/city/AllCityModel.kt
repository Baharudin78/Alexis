package com.alexis.shop.domain.model.city

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class AllCityModel (
    val data : List<CityItemModel> = mutableListOf()
)
@Parcelize
data class CityItemModel(
    val villageId : String,
    val fullName : String
): Parcelable