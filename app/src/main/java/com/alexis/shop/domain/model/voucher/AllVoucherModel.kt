package com.alexis.shop.domain.model.voucher

data class AllVoucherModel (
    val data : List<VoucherItemModel> = mutableListOf()
)
data class VoucherItemModel(
    val id : Int = 0,
    val name : String = "",
    val description : String = "",
    val discount : Int = 0,
    val expiredDate : String = "",
    val voucherTypeId : Int = 0,
    val voucherType : VoucerTypeModel? = null
)
data class VoucerTypeModel(
    val idType : Int = 0,
    val nameType : String = ""
)