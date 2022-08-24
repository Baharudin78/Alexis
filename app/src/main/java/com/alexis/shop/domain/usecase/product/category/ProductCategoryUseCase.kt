package com.alexis.shop.domain.usecase.product.category

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.product.category.ProductCategoryModel
import com.alexis.shop.domain.model.product.category.ProductCategoryModelList
import com.alexis.shop.domain.model.product.category.subcategory.SubCategoryModel
import kotlinx.coroutines.flow.Flow

interface ProductCategoryUseCase {
     fun getAllProductCategory(): Flow<Resource<ProductCategoryModelList>>
     fun getSubProductCategory(name : String) : Flow<Resource<List<SubCategoryModel>>>
}