package com.alexis.shop.data.repository.shoppingbag

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.datasource.ShoppingBagRemoteDataSource
import com.alexis.shop.data.remote.response.product.*
import com.alexis.shop.data.remote.response.shoppingbag.ShopingBagItem
import com.alexis.shop.data.remote.response.shoppingbag.ShopingProduct
import com.alexis.shop.data.remote.response.wishlist.delete.MessageResponse
import com.alexis.shop.domain.model.product.*
import com.alexis.shop.domain.model.shoppingbag.ShopingBagListModel
import com.alexis.shop.domain.model.shoppingbag.ShopingBagPostModel
import com.alexis.shop.domain.model.shoppingbag.ShopingProductModel
import com.alexis.shop.domain.model.shoppingbag.ShoppingBagModel
import com.alexis.shop.domain.repository.shoppingbag.IShoppingBagRepository
import com.alexis.shop.utils.orZero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingBagRepository @Inject constructor(
    private val remoteDataSource: ShoppingBagRemoteDataSource
) : IShoppingBagRepository {

    override fun postShoppingBag(productItemCode: String, productSizeId: String,quantity: String): Flow<Resource<ShopingBagPostModel>> {
        return flow<Resource<ShopingBagPostModel>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.postShoppingBag(productItemCode, productSizeId, quantity).first()) {
                is ApiResponse.Success -> emit(
                    Resource.Success(
                        ShopingBagPostModel(
                            id = apiResponse.data.shopingBagPostData?.id,
                            customerId = apiResponse.data.shopingBagPostData?.customerId,
                            productItemCode = apiResponse.data.shopingBagPostData?.productItemCode,
                            productSizeId = apiResponse.data.shopingBagPostData?.productSizeId,
                            unit = apiResponse.data.shopingBagPostData?.unit,
                            price = apiResponse.data.shopingBagPostData?.price,
                            finalPrice = apiResponse.data.shopingBagPostData?.finalPrice
                        )
                    )
                )
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun deleteShoppingBag(id: Int): Flow<Resource<MessageResponse>> {
        return flow<Resource<MessageResponse>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.deleteShoppingBag(id).first()) {
                is ApiResponse.Success -> emit(Resource.Success(apiResponse.data))
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun getShoppingBag(): Flow<Resource<ShopingBagListModel>> {
        return flow<Resource<ShopingBagListModel>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getShoppingBag().first()) {
                is ApiResponse.Success -> emit(
                    Resource.Success(
                        generateShoppingBagModel(apiResponse.data.data.items)
                    )
                )
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    private fun generateShoppingBagModel(data: List<ShopingBagItem?>?): ShopingBagListModel {
        return if (!data.isNullOrEmpty()) {
            ShopingBagListModel(
                bag = data.map {
                    ShoppingBagModel(
                        customer_id = it?.customer_id.orZero(),
                        final_price = it?.final_price.orZero(),
                        id = it?.id.orZero(),
                        price = it?.price.orZero(),
                        product = generateShopingProduct(it?.product),
                        product_item_code = it?.product_item_code.orEmpty(),
                        product_size_id = it?.product_size_id.orZero(),
                        unit = it?.unit.orZero()
                    )
                }
            )
        }else{
            ShopingBagListModel()
        }
    }

    private fun generateShopingProduct(data : ShopingProduct? ) : ShopingProductModel? {
        return ShopingProductModel(
            barcode = data?.barcode.orEmpty(),
            color_code = data?.color_code.orEmpty(),
            id = data?.id.orZero(),
            item_code = data?.item_code.orEmpty(),
            name = data?.name.orEmpty(),
            packaging_id = data?.packaging_id.orZero(),
            price = data?.price.orZero(),
            product_material_id = data?.product_material_id.orEmpty(),
            product_size_id = data?.product_size_id.orEmpty(),
            product_subcategory_id = data?.product_subcategory_id.orZero(),
            status = data?.status.orEmpty(),
            stock = data?.stock.orZero(),
            stock_keeping_unit = data?.stock_keeping_unit.toString().orEmpty(),
            store_location_id = data?.store_location_id.orZero(),
            style_code = data?.style_code.orEmpty(),
            user_id = data?.user_id.orZero(),
            weight = data?.weight.orZero()
        )
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

}