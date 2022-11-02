package com.alexis.shop.data.repository.product

import android.util.Log
import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.response.product.categoritwo.ProductCategoryItem
import com.alexis.shop.data.remote.response.product.categoritwo.SubCategoryItem
import com.alexis.shop.data.remote.response.product.category.ProductCategoryRemoteDataSource
import com.alexis.shop.domain.model.product.category.ProductCategoryNewModel
import com.alexis.shop.domain.model.product.category.SubCategoryModel
import com.alexis.shop.domain.repository.product.IProductCategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductCategoryRepository @Inject constructor(
    private val remoteDataSource: ProductCategoryRemoteDataSource
) : IProductCategoryRepository {

    override fun getAllProductCategory(): Flow<Resource<List<ProductCategoryNewModel>>> {
        return flow<Resource<List<ProductCategoryNewModel>>> {
            emit(Resource.Loading())
            when(val apiResponses = remoteDataSource.getAllProductCategory().first()) {
                is ApiResponse.Success -> emit(Resource.Success(generateCategoryList(apiResponses.data.data?.items)))
                is ApiResponse.Empty -> listOf<ProductCategoryNewModel>()
                is ApiResponse.Error -> emit(Resource.Error(apiResponses.errorMessage))
            }
        }
    }

    private fun generateCategoryList(data: List<ProductCategoryItem>?): List<ProductCategoryNewModel> {
        val generateValue = ArrayList<ProductCategoryNewModel>()
        data?.forEach {
            Log.e("RemoteDataS", "Good")
            generateValue.add(
                ProductCategoryNewModel(
                    category = it.category,
                    icon = it.icon,
                    sub_category = generateSubCategory(it.sub_category)
                )
            )
        }
        return generateValue
    }

    private fun generateSubCategory(data: List<SubCategoryItem>) : List<SubCategoryModel> {
        val generateValue = ArrayList<SubCategoryModel>()
        data.forEach {
            generateValue.add(
                SubCategoryModel(
                    id = it.id,
                    merchandise_name = it.merchandise_name
                )
            )
        }
        return generateValue
    }

}