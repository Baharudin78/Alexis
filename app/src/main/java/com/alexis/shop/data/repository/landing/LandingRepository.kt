package com.alexis.shop.data.repository.landing

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.datasource.LandingDataSource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.response.landing.LandingItem
import com.alexis.shop.data.remote.response.landing.LandingResponse
import com.alexis.shop.domain.model.landing.LandingModelItem
import com.alexis.shop.domain.repository.landing.ILandingRepository
import com.google.android.gms.common.api.Api
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LandingRepository @Inject constructor(
    private val remoteDataSource : LandingDataSource
) : ILandingRepository{
    override fun getLandingImage(): Flow<Resource<LandingModelItem>> {
        return channelFlow<Resource<LandingModelItem>> {
            send(Resource.Loading())
            when(val apiResponse = remoteDataSource.getLandingImage().first()) {
                is ApiResponse.Success -> send(Resource.Success(generateLandingItemToModel(apiResponse.data.data?.landingItem)))
                is ApiResponse.Empty -> LandingModelItem()
                is ApiResponse.Error -> send(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    private fun generateLandingItemToModel(data : LandingItem?) : LandingModelItem {
        return if (data != null){
            LandingModelItem(
                desktopLandingImage = data.desktopLandingImage,
                endDate = data.endDate,
                hyperlink = data.hyperlink,
                id = data.id,
                logoColorMobile = data.logoColorMobile,
                mobileLandingImage = data.mobileLandingImage,
                name = data.name,
                startDate = data.startDate
            )
        }else {
            LandingModelItem()
        }
    }


}