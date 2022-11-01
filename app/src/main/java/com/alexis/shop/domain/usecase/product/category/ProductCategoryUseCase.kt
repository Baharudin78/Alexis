package com.alexis.shop.domain.usecase.product.category

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.product.category.ProductCategoryNewModel
import kotlinx.coroutines.flow.Flow

interface ProductCategoryUseCase {
    fun getAllProductCategory(): Flow<Resource<List<ProductCategoryNewModel>>>
}