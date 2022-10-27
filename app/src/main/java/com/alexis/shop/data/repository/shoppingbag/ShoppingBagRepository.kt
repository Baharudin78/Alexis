package com.alexis.shop.data.repository.shoppingbag

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.response.shoppingbag.CartsItem
import com.alexis.shop.data.remote.datasource.ShoppingBagRemoteDataSource
import com.alexis.shop.data.remote.response.product.ExclusiveOfferItem
import com.alexis.shop.data.remote.response.product.ProductsGetByIdImagesItem
import com.alexis.shop.data.remote.response.product.ProductsGetByIdItem
import com.alexis.shop.domain.model.product.ProductExclusiveOffer
import com.alexis.shop.domain.model.product.ProductsByIdModel
import com.alexis.shop.domain.model.product.ProductsGetByIdImagesModel
import com.alexis.shop.domain.model.shoppingbag.ShopingBagPostModel
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

    override fun deleteShoppingBag(cardId: Int): Flow<Resource<String>> {
        return flow<Resource<String>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.deleteShoppingBag(cardId).first()) {
                is ApiResponse.Success -> emit(Resource.Success(apiResponse.data))
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun getShoppingBag(): Flow<Resource<List<ShoppingBagModel>>> {
        return flow<Resource<List<ShoppingBagModel>>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getShoppingBag().first()) {
                is ApiResponse.Success -> emit(
                    Resource.Success(
                        generateShoppingBagModel(apiResponse.data.data?.carts)
                    )
                )
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    private fun generateShoppingBagModel(data: List<CartsItem>?): List<ShoppingBagModel> {
        return data?.map {
            ShoppingBagModel(
                id = it.id,
                customerId = it.customerId,
                productItemCode = it.productItemCode,
                productSizeId = it.productSizeId,
                unit = it.unit,
                price = it.price,
                finalPrice = it.finalPrice,
                product = generateProductByIdModel(it.product)

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
                //  id = product.id.orZero(),
                stock = product.stock.orZero(),
                productName = product.productName.orEmpty(),
                images = generateProductByIdImagesModel(product.images) as MutableList<ProductsGetByIdImagesModel>,
                exclusiveOffer = generateExclusiveOfferToItemOffer(product.exclusiveOffer)
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
                image = it.image.orEmpty(),
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

}