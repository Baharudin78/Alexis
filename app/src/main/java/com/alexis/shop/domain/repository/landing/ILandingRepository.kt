package com.alexis.shop.domain.repository.landing

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.landing.LandingModel
import com.alexis.shop.domain.model.landing.LandingModelItem
import kotlinx.coroutines.flow.Flow

interface ILandingRepository {
    fun getLandingImage() : Flow<Resource<LandingModelItem>>
}