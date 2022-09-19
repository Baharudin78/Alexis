package com.alexis.shop.data.remote.model.kota

import com.alexis.shop.data.remote.model.voucher.VoucherItem
import com.alexis.shop.data.remote.model.voucher.VoucherList
import com.google.gson.annotations.SerializedName

data class CityResponse (
    @field:SerializedName("code")
    val code : Int? = null,
    @field:SerializedName("data")
    val data : CityList? = null,
    @field:SerializedName("status")
    val status : String
)
data class CityList(
    @field:SerializedName("items")
    val cityItem : List<CityItem>? = null
)

data class CityItem(
    val village_id : String,
    val full_name : String
)
