package com.alexis.shop.data.repository.sizefilter

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.sizefilter.SizeFilterRemoteDataSource
import com.alexis.shop.data.remote.sizefilter.SizesItemResponse
import com.alexis.shop.domain.model.sizefilter.SizeFilterModel
import com.alexis.shop.domain.repository.sizefilter.ISizeFilterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SizeFilterRepository @Inject constructor(
    private val remoteDataSource: SizeFilterRemoteDataSource
) : ISizeFilterRepository {

    override fun getSizeFilter(): Flow<Resource<List<SizeFilterModel>>> {
        return flow<Resource<List<SizeFilterModel>>> {
            when (val apiResponse = remoteDataSource.getSizeFilter().first()) {
                is ApiResponse.Success -> emit(Resource.Success(generateSizeFilterList(apiResponse.data)))
                is ApiResponse.Empty -> emit(Resource.Success(listOf()))
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    private fun generateSizeFilterList(data: List<SizesItemResponse>?): List<SizeFilterModel> {
        val generateValue = ArrayList<SizeFilterModel>()
        data?.forEach {
            generateValue.add(
                SizeFilterModel(
                    id = it.id.orEmpty(),
                    name = it.name.orEmpty(),
                    selection = it.selection.orEmpty(),
                   // createdAt = it.createdAt.orEmpty(),
                    //updatedAt = it.updatedAt.orEmpty()
                )
            )
        }
        return generateValue
    }
}