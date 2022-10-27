package com.alexis.shop.domain.usecase.landing

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.landing.LandingModelItem
import kotlinx.coroutines.flow.Flow

interface LandingUseCase {
    fun getLandingImage() : Flow<Resource<LandingModelItem>>
}