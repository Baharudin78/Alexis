package com.alexis.shop.domain.repository.product

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.product.category.ProductCategoryNewModel
import kotlinx.coroutines.flow.Flow

interface IProductCategoryRepository {
     fun getAllProductCategory(): Flow<Resource<ProductCategoryNewModel>>
}