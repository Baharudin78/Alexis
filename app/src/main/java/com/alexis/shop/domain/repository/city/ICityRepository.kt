package com.alexis.shop.domain.repository.city

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.city.AllCityModel
import kotlinx.coroutines.flow.Flow

interface ICityRepository {
    fun getAllCity(token : String, city : String) : Flow<Resource<AllCityModel>>
}