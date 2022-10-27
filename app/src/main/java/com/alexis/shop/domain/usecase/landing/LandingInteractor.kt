package com.alexis.shop.domain.usecase.landing

import com.alexis.shop.data.Resource
import com.alexis.shop.data.repository.landing.LandingRepository
import com.alexis.shop.domain.model.landing.LandingModelItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LandingInteractor @Inject constructor(
    private val repository : LandingRepository
) :LandingUseCase {
    override fun getLandingImage(): Flow<Resource<LandingModelItem>> {
        return repository.getLandingImage()
    }
}