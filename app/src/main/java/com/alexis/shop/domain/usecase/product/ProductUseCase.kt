package com.alexis.shop.domain.usecase.product

import com.alexis.shop.domain.model.product.ProductsModel
import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.product.ProductsByIdModel
import kotlinx.coroutines.flow.Flow

interface ProductUseCase {
    fun getAllProduct(): Flow<Resource<List<ProductsModel>>>
    fun getProductById(productId: Int): Flow<Resource<ProductsByIdModel>>
}