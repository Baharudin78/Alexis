package com.alexis.shop.domain.usecase.product.category

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.product.category.ProductCategoryNewModel
import com.alexis.shop.domain.repository.product.IProductCategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductCategoryInteractor @Inject constructor(
    private val repository: IProductCategoryRepository):
    ProductCategoryUseCase {
    override fun getAllProductCategory(): Flow<Resource<ProductCategoryNewModel>> {
        return repository.getAllProductCategory()
    }
}