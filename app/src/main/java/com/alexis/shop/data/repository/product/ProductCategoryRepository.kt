package com.alexis.shop.data.repository.product

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.response.product.category.ProductCategoryItemResponse
import com.alexis.shop.data.remote.response.product.category.ProductCategoryRemoteDataSource
import com.alexis.shop.data.remote.response.product.category.SubCategoryModelBaru
import com.alexis.shop.data.remote.response.product.category.subcategory.SubProductCategoryItemResponse
import com.alexis.shop.domain.model.product.category.ProductCategoryModel
import com.alexis.shop.domain.model.product.category.subcategory.SubCategoryModel
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

    override suspend fun getSubCategoryProduct(): Resource<List<SubCategoryModel>> {
        return when(val apiResponseSub = remoteDataSource.getSubProductCategory().first()) {
            is ApiResponse.Success -> Resource.Success(generateSubCategoryList(apiResponseSub.data.data?.subProductCategory))
            is ApiResponse.Empty -> Resource.Success(listOf())
            is ApiResponse.Error -> Resource.Error(apiResponseSub.errorMessage)
        }
    }

    private fun generateCategoryList(data: List<ProductCategoryItemResponse>?): List<ProductCategoryModel> {
        val generateValue = ArrayList<ProductCategoryModel>()
        data?.forEach {
            generateValue.add(
                ProductCategoryModel(
                    category = it.category.orEmpty(),
                    icon = it.icon.orEmpty()
                )
            )
        }
        return generateValue
    }

    private fun generateSubCategoryList(data : List<SubProductCategoryItemResponse>?) : List<SubCategoryModel> {
        val generateValue = ArrayList<SubCategoryModel>()
        data?.forEach {
            generateValue.add(
                SubCategoryModel(
                    id = it.id.orZero(),
                    merchandiseName = it.merchandiseName.orEmpty()
                )
            )
        }
        return generateValue
    }
}