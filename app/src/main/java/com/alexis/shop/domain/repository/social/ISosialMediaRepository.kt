package com.alexis.shop.domain.repository.social

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.sosmed.SosialMediaModel
import kotlinx.coroutines.flow.Flow

interface ISosialMediaRepository {
    fun getSocialMedia() : Flow<Resource<SosialMediaModel>>
}