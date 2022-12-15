package com.alexis.shop.data.repository.sosmed

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.datasource.SocialMediaDataSource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.response.sosmed.toSosialMediaModel
import com.alexis.shop.domain.model.sosmed.SosialMediaModel
import com.alexis.shop.domain.repository.social.ISosialMediaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SosialMediaRepository @Inject constructor(
    private val sosialDataSource: SocialMediaDataSource
) : ISosialMediaRepository {
    override fun getSocialMedia(): Flow<Resource<SosialMediaModel>> {
        return flow<Resource<SosialMediaModel>> {
            when(val apiResponse = sosialDataSource.getSocialMedia().first()) {
                is ApiResponse.Success -> emit(Resource.Success(apiResponse.data.data.item!!.toSosialMediaModel()))
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }
}