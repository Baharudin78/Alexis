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
    @field:SerializedName("description")
    val description : String,
    @field:SerializedName("discount")
    val discount : Int,
    @field:SerializedName("expired_date")
    val expiredDate : String,
    @field:SerializedName("voucher_type_id")
    val voucherTypeId : Int,
    @field:SerializedName("voucher_type")
    val voucherType : VoucherType
)
data class VoucherType(
    @field:SerializedName("id")
    val idVoucherType : Int,
    @field:SerializedName("name")
    val nameType : String
)