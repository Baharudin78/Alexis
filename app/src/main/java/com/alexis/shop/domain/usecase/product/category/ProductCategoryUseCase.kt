package com.alexis.shop.domain.usecase.product.category

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.product.category.ProductCategoryNewModel
import com.alexis.shop.domain.model.product.modelbaru.ProductBaruModel
import kotlinx.coroutines.flow.Flow

interface ProductCategoryUseCase {
    fun getAllProductCategory(): Flow<Resource<ProductCategoryNewModel>>
    fun getCatergoryById(id : String) : Flow<Resource<List<ProductBaruModel>>>
}