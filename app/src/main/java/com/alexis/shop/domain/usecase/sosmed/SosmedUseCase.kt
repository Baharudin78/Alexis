package com.alexis.shop.domain.usecase.sosmed

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.sosmed.SosialMediaModel
import kotlinx.coroutines.flow.Flow

interface SosmedUseCase {
    fun getSosmed() : Flow<Resource<SosialMediaModel>>
}