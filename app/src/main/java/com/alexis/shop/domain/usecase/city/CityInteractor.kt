package com.alexis.shop.domain.usecase.city

import com.alexis.shop.data.Resource
import com.alexis.shop.data.repository.city.CityRepository
import com.alexis.shop.domain.model.city.AllCityModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CityInteractor @Inject constructor(
    private val cityRepository : CityRepository
) : CityUseCase {
    override fun getAllVoucher(token: String, name: String): Flow<Resource<AllCityModel>> {
        return cityRepository.getAllCity(token,name)
    }
}