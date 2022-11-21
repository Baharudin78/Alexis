package com.alexis.shop.domain.model.voucher

import com.google.gson.annotations.SerializedName

data class AllVoucherModel (
    val data : List<VoucherItemModel> = mutableListOf()
)
data class VoucherItemModel(
    val id : Int?= null,
    val name : String?= null,
    val icon : String?= null,
    val expiry : Int?= null,
    val expiredDate : String?= null,
    val launch : String?= null,
    val availableIn : Int?= null,
    val voucherTypeId : Int?= null,
    val amount : Int?= null,
    val condition : Int?= null,
    val minimumPurchase : Int?= null
)