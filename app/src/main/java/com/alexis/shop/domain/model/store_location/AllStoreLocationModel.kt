package com.alexis.shop.domain.model.store_location

data class AllStoreLocationModel(
    var data: List<AllStoreItemModel> = mutableListOf()
)

data class AllStoreItemModel(
    var province: String = "",
    var name : String = "",
    var city : String = "",
    var phoneNumber : String ="",
    var openTime : String ="",
    var closeTime : String = ""
)
