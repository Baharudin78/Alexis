package com.alexis.shop.domain.usecase.city

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.city.AllCityModel
import com.alexis.shop.domain.model.voucher.AllVoucherModel
import kotlinx.coroutines.flow.Flow

interface CityUseCase {
    fun getAllVoucher(token : String, name : String): Flow<Resource<AllCityModel>>
}