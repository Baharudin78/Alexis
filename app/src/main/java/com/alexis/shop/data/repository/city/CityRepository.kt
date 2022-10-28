package com.alexis.shop.data.repository.city

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.datasource.CityRemoteDataSource
import com.alexis.shop.data.remote.response.kota.CityItem
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.domain.model.city.AllCityModel
import com.alexis.shop.domain.model.city.CityItemModel
import com.alexis.shop.domain.repository.city.ICityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepository @Inject constructor(
    private val remoteDataSource : CityRemoteDataSource
): ICityRepository{
    override fun getAllCity(city: String): Flow<Resource<AllCityModel>> {
        return flow<Resource<AllCityModel>>{
            when(val apiResponse = remoteDataSource.getCity(city).first()) {
                is ApiResponse.Success -> emit(
                    Resource.Success(
                        convertCityResponseToModel(
                            apiResponse.data.data?.cityItem
                        )
                    )
                )
            }
        }
    }
    private fun convertCityResponseToModel(data : List<CityItem?>?) : AllCityModel {
        return if (!data.isNullOrEmpty()) {
            AllCityModel(
                data = data.map {
                    CityItemModel(
                       villageId = it?.village_id.orEmpty(),
                       fullName = it?.full_name.orEmpty()
                    )
                }
            )
        }else AllCityModel()
    }
}