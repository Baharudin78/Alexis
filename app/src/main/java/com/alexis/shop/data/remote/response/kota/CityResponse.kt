package com.alexis.shop.data.remote.response.kota

import com.google.gson.annotations.SerializedName

data class CityResponse (
    @SerializedName("code")
    val code : Int? = null,
    @SerializedName("data")
    val data : CityList? = null,
    @SerializedName("status")
    val status : String
)
data class CityList(
    @SerializedName("items")
    val cityItem : List<CityItem>? = null
)

data class CityItem(
    @SerializedName("village_id")
    val village_id : String,
    @SerializedName("full_name")
    val full_name : String
)
