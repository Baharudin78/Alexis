package com.alexis.shop.data.remote.response.voucher

import com.google.gson.annotations.SerializedName

data class VoucherResponse (
    @SerializedName("code")
    val code : Int? = null,
    @SerializedName("data")
    val data : VoucherList? = null,
    @SerializedName("status")
    val status : String
)
data class VoucherList(
    @SerializedName("items")
    val voucherList : List<VoucherItem>? = null
)
data class VoucherItem(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("icon")
    val icon : String,
    @SerializedName("expiry")
    val expiry : Int,
    @SerializedName("expiry_date")
    val expiredDate : String,
    @SerializedName("launch")
    val launch : String,
    @SerializedName("available_in")
    val availableIn : Int,
    @SerializedName("voucher_type_id")
    val voucherTypeId : Int,
    @SerializedName("amount")
    val amount : Int,
    @SerializedName("condition")
    val condition : Int,
    @SerializedName("minimum_purchase")
    val minimumPurchase : Int
)