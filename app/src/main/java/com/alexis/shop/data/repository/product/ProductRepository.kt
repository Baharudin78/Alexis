package com.alexis.shop.data.repository.product

import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.domain.repository.product.IProductRepository
import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.model.productbaru.ProductItems
import com.alexis.shop.data.remote.product.*
import com.alexis.shop.domain.model.product.*
import com.alexis.shop.domain.model.product.modelbaru.ProductBaruModel
import com.alexis.shop.utils.orZero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource
) : IProductRepository {

    override fun getAllProduct(): Flow<Resource<List<ProductBaruModel>>> {
        return flow<Resource<List<ProductBaruModel>>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getAllProduct().first()) {
                is ApiResponse.Success -> emit(Resource.Success(generateListProducts(apiResponse.data.data?.product)))
                is ApiResponse.Empty -> listOf<ProductBaruModel>()
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun getProductById(productId: Int): Flow<Resource<ProductsByIdModel>> {
        return flow<Resource<ProductsByIdModel>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getProductById(productId).first()) {
                is ApiResponse.Success -> emit(Resource.Success(generateProductByIdModel(apiResponse.data.data?.product)))
                is ApiResponse.Empty -> ProductsByIdModel()
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    private fun generateListProducts(products: List<ProductItems>?): List<ProductBaruModel> {
        return products?.map {
//            ProductsModel(
//                thumbnail = it.thumbnail.orEmpty(),
//                updatedAt = it.updatedAt.orEmpty(),
//                price = it.price.orZero(),
//                createdAt = it.createdAt.orEmpty(),
//                id = it.id.orZero(),
//                indonesiaName = it.indonesiaName.orEmpty(),
//                stock = it.stock.orZero(),
//                barcode = it.barcode.orEmpty(),
//                englishName = it.englishName.orEmpty(),
//                imageType = it.imageType.orEmpty()
//            )
            ProductBaruModel(
                name = it.name,
                product_image = it.product_image,
                price = it.price,
                product_id = it.product_id,
                stock = it.stock,
                barcode = it.barcode,
            )
        } ?: listOf()
    }

    private fun generateProductByIdModel(product: ProductsGetByIdItem?): ProductsByIdModel {
        return if(product != null) {
            ProductsByIdModel(
                categoryName = product.categoryName.orEmpty(),
                createdAt = product.createdAt.orEmpty(),
                price = product.price.orZero(),
                productId = product.productId.orZero(),
                weight = product.weight.orZero(),
                updatedAt = product.updatedAt.orEmpty(),
                id = product.id.orZero(),
                stock = product.stock.orZero(),
                productName = product.productName.orEmpty(),
                images = generateProductByIdImagesModel(product.images) as MutableList<ProductsGetByIdImagesModel>,
                size = generateProductByIdSizeModel(product.size) as MutableList<ProductsGetByIdSizeModel>,
                material = generateProductByIdMaterialModel(product.material) as MutableList<ProductsGetByIdMaterialModel>
            )
        } else {
            ProductsByIdModel()
        }
    }

    private fun generateProductByIdImagesModel(data: List<ProductsGetByIdImagesItem>?): List<ProductsGetByIdImagesModel> {
        return data?.map {
            ProductsGetByIdImagesModel(
                image = it.image.orEmpty(),
                type = it.type.orEmpty()
            )
        } ?: mutableListOf()
    }

    private fun generateProductByIdSizeModel(data: List<ProductsGetByIdSizeItem>?): List<ProductsGetByIdSizeModel> {
        return data?.map {
            ProductsGetByIdSizeModel(
                name = it.name.orEmpty(),
                id = it.id.orEmpty()
            )
        } ?: mutableListOf()
    }

    private fun generateProductByIdMaterialModel(data: List<ProductsGetByIdMaterialItem>?): List<ProductsGetByIdMaterialModel> {
        return data?.map {
            ProductsGetByIdMaterialModel(
                name = it.name.orEmpty(),
                id = it.id.orEmpty()
            )
        } ?: mutableListOf()
    }
}