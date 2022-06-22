package com.alexis.shop.domain.model.store_location

data class AllStoreLocationModel(
    var data: List<AllStoreItemModel> = mutableListOf()
)

data class AllStoreItemModel(
    var province: String = ""
)
