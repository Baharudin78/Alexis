package com.alexis.shop.data.repository.sizefilter

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.datasource.SizeFilterRemoteDataSource
import com.alexis.shop.data.remote.response.productbaru.ImageModel
import com.alexis.shop.data.remote.response.productbaru.ProductItems
import com.alexis.shop.data.remote.response.sizefilter.SizesItemResponse
import com.alexis.shop.domain.model.product.modelbaru.ImagesModel
import com.alexis.shop.domain.model.product.modelbaru.ProductBaruModel
import com.alexis.shop.domain.model.sizefilter.SizeFilterModel
import com.alexis.shop.domain.repository.sizefilter.ISizeFilterRepository
import com.alexis.shop.utils.orZero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SizeFilterRepository @Inject constructor(
    private val remoteDataSource: SizeFilterRemoteDataSource
) : ISizeFilterRepository {

    override fun getSizeFilter(): Flow<Resource<List<SizeFilterModel>>> {
        return flow<Resource<List<SizeFilterModel>>> {
            when (val apiResponse = remoteDataSource.getSizeFilter().first()) {
                is ApiResponse.Success -> emit(Resource.Success(generateSizeFilterList(apiResponse.data)))
                is ApiResponse.Empty -> emit(Resource.Success(listOf()))
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun postProductFilter(sizeId:  IntArray): Flow<Resource<List<ProductBaruModel>>> {
        return flow<Resource<List<ProductBaruModel>>> {
            emit(Resource.Loading())
            when(val apiResponse = remoteDataSource.postSizeFilter(sizeId).first()) {
                is ApiResponse.Success ->
                    emit(Resource.Success(generateListProducts(apiResponse.data.data?.product)))
                is ApiResponse.Empty -> listOf<ProductBaruModel>()
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    private fun generateSizeFilterList(data: List<SizesItemResponse>?): List<SizeFilterModel> {
        val generateValue = ArrayList<SizeFilterModel>()
        data?.forEach {
            generateValue.add(
                SizeFilterModel(
                    id = it.id.orEmpty(),
                    name = it.name.orEmpty(),
                    selection = it.selection.orEmpty(),
                   // createdAt = it.createdAt.orEmpty(),
                    //updatedAt = it.updatedAt.orEmpty()
                )
            )
        }
        return generateValue
    }

    private fun generateListProducts(products: List<ProductItems>?): List<ProductBaruModel> {
        return products?.map {
            ProductBaruModel(
                id = it.id.orZero(),
                barcode = it.barcode.orEmpty(),
                name = it.name.orEmpty(),
                product_image = generateProductImage(it.product_image),
                price = it.price.orZero(),
                stock = it.stock.orZero(),
                status = it.status.orEmpty(),
                subcategory_id = it.subcategory_id.orZero(),
                weight = it.weight.orZero(),
                stock_keeping_unit = it.stock_keeping_unit.orEmpty(),
                product_size_id = it.product_size_id.orEmpty(),
                material_id = it.product_material_id.orEmpty(),
                item_code = it.item_code.orEmpty(),
                color_id = it.color_id.orEmpty(),
                change_to_stored = it.change_to_stored.orEmpty(),
                change_to_listed = it.change_to_listed.orEmpty(),
                style_id = it.style_id.orEmpty()
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
}