package com.alexis.shop.domain.model.city

data class AllCityModel (
    val data : List<CityItemModel> = mutableListOf()
)
data class CityItemModel(
    val villageId : String,
    val fullName : String
)