package com.alexis.shop.domain.repository.product

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.response.productbaru.ProductBaruResponse
import com.alexis.shop.domain.model.product.category.ProductCategoryNewModel
import com.alexis.shop.domain.model.product.modelbaru.AllProductBaruModel
import com.alexis.shop.domain.model.product.modelbaru.ProductBaruModel
import kotlinx.coroutines.flow.Flow

interface IProductCategoryRepository {
     fun getAllProductCategory(): Flow<Resource<ProductCategoryNewModel>>
     fun getCategoryById(id : Int) : Flow<Resource<AllProductBaruModel>>
}