package com.alexis.shop.data.repository.product

import android.util.Log
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.domain.repository.product.IProductRepository
import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.model.productbaru.ImageModel
import com.alexis.shop.data.remote.model.productbaru.ProductItems
import com.alexis.shop.data.remote.product.*
import com.alexis.shop.domain.model.product.*
import com.alexis.shop.domain.model.product.modelbaru.ImagesModel
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
                is ApiResponse.Success ->
                    emit(Resource.Success(generateListProducts(apiResponse.data.data?.product)))
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
            ProductBaruModel(
                barcode = it.barcode,
                name = it.name,
                product_image = generateProductImage(it.product_image),
                price = it.price,
                product_id = it.product_id,
                stock = it.stock,
                status = it.status,
                subcategory_id = it.subcategory_id,
                weight = it.weight,
                stock_keeping_unit = it.stock_keeping_unit,
                size_id = it.size_id,
                material_id = it.material_id,
                item_code = it.item_code,
                color_id = it.color_id,
                change_to_stored = it.change_to_stored,
                change_to_listed = it.change_to_listed,
                style_id = it.style_id
            )
        } ?: listOf()
    }

    private fun generateProductImage(image : List<ImageModel>?) : List<ImagesModel> {
        return image?.map {
            ImagesModel(
                bag_wishlist_order_display = it.bag_wishlist_order_display,
                image_url = it.image_url,
                product_detail_display = it.product_detail_display,
                product_image_id = it.product_image_id,
                product_item_code = it.product_item_code,
                product_list_display = it.product_list_display,
                type = it.type
            )
        } ?: mutableListOf()
    }
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