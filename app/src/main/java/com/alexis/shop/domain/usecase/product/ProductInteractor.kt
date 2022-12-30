package com.alexis.shop.domain.usecase.product

import com.alexis.shop.domain.repository.product.IProductRepository
import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.product.ProductsByIdModel
import com.alexis.shop.domain.model.product.modelbaru.AllProductBaruModel
import com.alexis.shop.domain.model.product.modelbaru.ProductBaruModel
import com.alexis.shop.domain.model.product.size.ProductSizeListModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductInteractor @Inject constructor(
    private val repository: IProductRepository
    ): ProductUseCase {
    override fun getAllProduct(): Flow<Resource<List<ProductBaruModel>>> {
        return repository.getAllProduct()
    }

    override fun getProductNewIn(): Flow<Resource<List<ProductBaruModel>>>{
        return repository.getProductNewIn()
    }

    override fun getProductById(productId: Int): Flow<Resource<ProductsByIdModel>> {
        return repository.getProductById(productId)
    }

    override fun getProductByBarcode(barcode: String): Flow<Resource<ProductsByIdModel>> {
        return repository.getProductByBarcode(barcode)
    }

    override fun getProductSize(id: Int): Flow<Resource<ProductSizeListModel>> {
        return repository.getProductSize(id)
    }
}