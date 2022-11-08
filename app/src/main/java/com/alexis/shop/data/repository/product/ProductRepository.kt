package com.alexis.shop.data.repository.product

import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.domain.repository.product.IProductRepository
import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.datasource.ProductRemoteDataSource
import com.alexis.shop.data.remote.response.productbaru.ImageModel
import com.alexis.shop.data.remote.response.productbaru.ProductItems
import com.alexis.shop.data.remote.response.product.*
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
                size_id = it.size_id.orEmpty(),
                material_id = it.material_id.orEmpty(),
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

    private fun generateProductByIdModel(product: ProductsGetByIdItem?): ProductsByIdModel {
        return if(product != null) {
            ProductsByIdModel(
                id = product.productId,
                barcode = product.barcode.orEmpty(),
                stockKeepingUnit = product.stockKeepingUnit.orEmpty(),
                itemCode = product.itemCode.orEmpty(),
                productName = product.productName.orEmpty(),
                productSubcategoryId = product.productSubcategoryId.orEmpty(),
                stock = product.stock.orZero(),
                price = product.price.orZero(),
                weight = product.weight.orZero(),
                styleCode = product.styleCode.orEmpty(),
                productMaterialId = product.productMaterialId.orEmpty(),
                colorCode = product.colorCode.orEmpty(),
                productSizeId = product.productSizeId.orEmpty(),
                packagingId = product.packagingId.orEmpty(),
                status = product.status.orEmpty(),
                storeLocationId = product.storeLocationId.orZero(),
                userId = product.userId.orZero(),
                images = generateProductByIdImagesModel(product.images) as MutableList<ProductsGetByIdImagesModel>,
                exclusiveOffer = generateExclusiveOfferToItemOffer(product.exclusiveOffer),
                productSize = generateProductSize(product.productSizeItem),
                productMaterial = generateProductMaterial(product.productMaterialItem)
               // size = generateProductByIdSizeModel(product.size) as MutableList<ProductsGetByIdSizeModel>,
             //   material = generateProductByIdMaterialModel(product.material) as MutableList<ProductsGetByIdMaterialModel>
            )
        } else {
            ProductsByIdModel()
        }
    }

    private fun generateProductByIdImagesModel(data: List<ProductsGetByIdImagesItem>?): List<ProductsGetByIdImagesModel> {
        return data?.map {
            ProductsGetByIdImagesModel(
                image = it.imageUrl.orEmpty(),
                type = it.type.orEmpty()
            )
        } ?: mutableListOf()
    }

    private fun generateExclusiveOfferToItemOffer(data : ExclusiveOfferItem?) : ProductExclusiveOffer?{
        return ProductExclusiveOffer(
            id = data?.id.orZero(),
            productItemCode = data?.productTtemCode.orEmpty(),
            redemptionPoint = data?.redemptionPoint.orZero(),
            aging = data?.aging.orZero(),
            registrationDate = data?.registrationDate.orEmpty()
        )
    }

    private fun generateProductMaterial(data : ProductsGetByIdMaterialItem?) : ProductsGetByIdMaterialModel? {
        return ProductsGetByIdMaterialModel(
            name = data?.name.orEmpty(),
            id = data?.id.orEmpty()
        )
    }

    private fun generateProductSize(data : ProductsGetByIdSizeItem?) : ProductsGetByIdSizeModel? {
        return ProductsGetByIdSizeModel(
            name = data?.name.orEmpty(),
            id = data?.id.orEmpty()
        )
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