package com.alexis.shop.domain.usecase.product

import com.alexis.shop.domain.model.product.ProductsModel
import com.alexis.shop.domain.repository.product.IProductRepository
import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.product.ProductsByIdModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductInteractor @Inject constructor(private val repository: IProductRepository): ProductUseCase {
    override fun getAllProduct(): Flow<Resource<List<ProductsModel>>> {
        return repository.getAllProduct()
    }

    override fun getProductById(productId: Int): Flow<Resource<ProductsByIdModel>> {
        return repository.getProductById(productId)
    }
}