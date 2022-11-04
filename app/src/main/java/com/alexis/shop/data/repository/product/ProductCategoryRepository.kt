package com.alexis.shop.data.repository.product

import android.util.Log
import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.response.product.categoritwo.ProductCategoryItem
import com.alexis.shop.data.remote.response.product.categoritwo.ProductCategoryNewResponse
import com.alexis.shop.data.remote.response.product.categoritwo.SubCategoryItem
import com.alexis.shop.data.remote.response.product.category.ProductCategoryRemoteDataSource
import com.alexis.shop.domain.model.product.category.ProductCategoryNewItem
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

    override fun getAllProductCategory(): Flow<Resource<ProductCategoryNewModel>> {
        return flow<Resource<ProductCategoryNewModel>> {
            emit(Resource.Loading())
            when(val apiResponses = remoteDataSource.getAllProductCategory().first()) {
                is ApiResponse.Success -> emit(
                    Resource.Success(
                        generateCategoryList(apiResponses.data.data.items)
                    )
                )
                is ApiResponse.Empty -> listOf<ProductCategoryNewModel>()
                is ApiResponse.Error -> emit(Resource.Error(apiResponses.errorMessage))
            }
        }
    }

    private fun generateCategoryList(data: List<ProductCategoryItem?>?): ProductCategoryNewModel {
       return if (!data.isNullOrEmpty()) {
           ProductCategoryNewModel(
               data = data.map {
                   ProductCategoryNewItem(
                       category = it?.category.orEmpty(),
                       icon = it?.icon.orEmpty(),
                       sub_category = generateSubCategory(it?.sub_category),
                       isExpandable = it!!.isExpandable
                   )
               }
           )
       }else ProductCategoryNewModel()
    }

    private fun generateSubCategory(data: List<SubCategoryItem>?) : List<SubCategoryModel> {
        val generateValue = ArrayList<SubCategoryModel>()
        data?.forEach {
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