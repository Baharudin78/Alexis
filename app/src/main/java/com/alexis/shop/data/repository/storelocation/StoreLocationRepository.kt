package com.alexis.shop.data.repository.storelocation

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.storelocation.AllStoreItemResponse
import com.alexis.shop.data.remote.storelocation.StoreLocationItem
import com.alexis.shop.data.remote.storelocation.StoreLocationRemoteDataSource
import com.alexis.shop.domain.model.store_location.AllStoreItemModel
import com.alexis.shop.domain.model.store_location.AllStoreLocationModel
import com.alexis.shop.domain.model.store_location.StoreLocationByNameModel
import com.alexis.shop.domain.repository.storelocation.IStoreLocationRepository
import com.alexis.shop.utils.orZero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreLocationRepository @Inject constructor(
    private val remoteDataSource: StoreLocationRemoteDataSource
) : IStoreLocationRepository {
    override fun getAllStoreLocation(): Flow<Resource<AllStoreLocationModel>> {
        return flow<Resource<AllStoreLocationModel>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getAllStoreLocation().first()) {
                is ApiResponse.Success -> emit(
                    Resource.Success(
                        convertAllStoreResponseToModel(
                            apiResponse.data.data?.storeLocation
                        )
                    )
                )
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun getStoreLocationByName(name: String): Flow<Resource<List<StoreLocationByNameModel>>> {
        return flow<Resource<List<StoreLocationByNameModel>>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getStoreLocationByName(name).first()) {
                is ApiResponse.Success -> emit(Resource.Success(generateStoreByNameModel(apiResponse.data.data?.storeLocation)))
                is ApiResponse.Empty -> listOf<StoreLocationByNameModel>()
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    private fun convertAllStoreResponseToModel(data: List<AllStoreItemResponse?>?): AllStoreLocationModel {
        return if (!data.isNullOrEmpty()) {
            AllStoreLocationModel(
                data = data.map {
                    AllStoreItemModel(
                        province = it?.province.orEmpty()
                    )
                }
            )
        } else AllStoreLocationModel()
    }

    private fun generateStoreByNameModel(data: List<StoreLocationItem>?): List<StoreLocationByNameModel> {
        return data?.map {
            StoreLocationByNameModel(
                id = it.id.orZero(),
                province = it.province.orEmpty(),
                updatedAt = it.updatedAt.orEmpty(),
                city = it.city.orEmpty(),
                latitude = it.latitude.orZero(),
                name = it.name.orEmpty(),
                openTime = it.openTime.orEmpty(),
                createdAt = it.createdAt.orEmpty(),
                phoneNumber = it.phoneNumber.orEmpty(),
                closeTime = it.closeTime.orEmpty(),
                longitude = it.longitude.orZero()
            )
        } ?: listOf()
    }
}