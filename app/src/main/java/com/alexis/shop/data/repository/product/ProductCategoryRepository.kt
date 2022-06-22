package com.alexis.shop.data.repository.product

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.product.category.ProductCategoryItemResponse
import com.alexis.shop.data.remote.product.category.ProductCategoryRemoteDataSource
import com.alexis.shop.domain.model.product.category.ProductCategoryModel
import com.alexis.shop.domain.repository.product.IProductCategoryRepository
import com.alexis.shop.utils.orZero
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductCategoryRepository @Inject constructor(
    private val remoteDataSource: ProductCategoryRemoteDataSource
) : IProductCategoryRepository {

    override suspend fun getAllProductCategory(): Resource<List<ProductCategoryModel>> {
        return when (val apiResponse = remoteDataSource.getAllProductCategory().first()) {
            is ApiResponse.Success -> Resource.Success(generateCategoryList(apiResponse.data.data?.productCategory))
            is ApiResponse.Empty -> Resource.Success(listOf())
            is ApiResponse.Error -> Resource.Error(apiResponse.errorMessage)
        }
    }

    private fun generateCategoryList(data: List<ProductCategoryItemResponse>?): List<ProductCategoryModel> {
        val generateValue = ArrayList<ProductCategoryModel>()
        data?.forEach {
            generateValue.add(
                ProductCategoryModel(
                    id = it.id.orZero(),
                    nameInEng = it.nameInEng.orEmpty(),
                    nameInId = it.nameInId.orEmpty(),
                    createdAt = it.createdAt.orEmpty(),
                    updatedAt = it.updatedAt.orEmpty()
                )
            )
        }
        return generateValue
    }
}