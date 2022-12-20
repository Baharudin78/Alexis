package com.alexis.shop.domain.usecase.product

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.product.ProductsByIdModel
import com.alexis.shop.domain.model.product.modelbaru.ProductBaruModel
import com.alexis.shop.domain.model.product.size.ProductSizeListModel
import kotlinx.coroutines.flow.Flow

interface ProductUseCase {
    fun getAllProduct(): Flow<Resource<List<ProductBaruModel>>>
    fun getProductById(productId: Int): Flow<Resource<ProductsByIdModel>>
    fun getProductByBarcode(barcode : String) : Flow<Resource<ProductsByIdModel>>
    fun getProductSize(id : Int) : Flow<Resource<ProductSizeListModel>>
}