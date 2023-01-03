package com.alexis.shop.domain.model.store_location

data class AllStoreLocationModel(
    var data: List<AllStoreItemModel> = mutableListOf()
)

data class AllStoreItemModel(
    var id : Int = 0,
    var storeArea : Int = 0,
    var province: String = "",
    var name : String = "",
    var city : String = "",
    var phoneNumber : String ="",
    var openTime : String ="",
    var closeTime : String = "",
    var latitude : String = "",
    var longitude : String = "",
    var imageUrl : String = ""
)
