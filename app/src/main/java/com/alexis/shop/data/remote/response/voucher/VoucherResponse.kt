package com.alexis.shop.data.remote.response.voucher

import com.google.gson.annotations.SerializedName

data class VoucherResponse (
    @field:SerializedName("code")
    val code : Int? = null,
    @field:SerializedName("data")
    val data : VoucherList? = null,
    @field:SerializedName("status")
    val status : String
)
data class VoucherList(
    @field:SerializedName("items")
    val voucherList : List<VoucherItem>? = null
)
data class VoucherItem(
    @field:SerializedName("id")
    val id : Int,
    @field:SerializedName("name")
    val name : String,
    @field:SerializedName("icon")
    val icon : String,
    @field:SerializedName("expiry")
    val expiry : Int,
    @field:SerializedName("expiry_date")
    val expiredDate : String,
    @field:SerializedName("launch")
    val launch : String,
    @field:SerializedName("available_in")
    val availableIn : Int,
    @field:SerializedName("voucher_type_id")
    val voucherTypeId : Int,
    @field:SerializedName("amount")
    val amount : Int,
    @field:SerializedName("condition")
    val condition : Int,
    @field:SerializedName("minimum_purchase")
    val minimumPurchase : Int
)