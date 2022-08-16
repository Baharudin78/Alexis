package com.alexis.shop.domain.usecase.product.category

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.product.category.ProductCategoryModel
import com.alexis.shop.domain.model.product.category.subcategory.SubCategoryModel
import kotlinx.coroutines.flow.Flow

interface ProductCategoryUseCase {
    suspend fun getAllProductCategory(): Resource<List<ProductCategoryModel>>
    suspend fun getSubProductCategory(name : String) : Resource<List<SubCategoryModel>>
}