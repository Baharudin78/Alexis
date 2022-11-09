package com.alexis.shop.domain.repository.product

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.product.ProductsByIdModel
import com.alexis.shop.domain.model.product.barcode.ProductsByBarcodeModel
import com.alexis.shop.domain.model.product.modelbaru.ProductBaruModel
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    fun getAllProduct(): Flow<Resource<List<ProductBaruModel>>>
    fun getProductById(productId: Int): Flow<Resource<ProductsByIdModel>>
    fun getProductByBarcode(barcode : String) : Flow<Resource<ProductsByIdModel>>
}