package com.alexis.shop.domain.usecase.sosmed

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.sosmed.SosialMediaModel
import com.alexis.shop.domain.repository.social.ISosialMediaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SosmedInteractor @Inject constructor(
    private val sosmedRepository: ISosialMediaRepository
) : SosmedUseCase{
    override fun getSosmed(): Flow<Resource<SosialMediaModel>> {
        return sosmedRepository.getSocialMedia()
    }
}