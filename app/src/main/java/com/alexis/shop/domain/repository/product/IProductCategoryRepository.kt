package com.alexis.shop.domain.repository.product

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.product.category.subcategory.SubProductCategoryResponse
import com.alexis.shop.domain.model.product.category.ProductCategoryModel
import kotlinx.coroutines.flow.Flow

interface IProductCategoryRepository {
    suspend fun getAllProductCategory(): Resource<List<ProductCategoryModel>>
    //suspend fun getSubCategoryProduct() : Resource<List<SubProductCategoryResponse>>
}