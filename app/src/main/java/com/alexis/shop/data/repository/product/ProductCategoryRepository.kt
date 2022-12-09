package com.alexis.shop.data.repository.product

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.response.product.category.ProductCategoryItem
import com.alexis.shop.data.remote.response.product.category.SubCategoryItem
import com.alexis.shop.data.remote.datasource.ProductCategoryRemoteDataSource
import com.alexis.shop.data.remote.response.productbaru.ImageModel
import com.alexis.shop.data.remote.response.productbaru.ProductBaruResponse
import com.alexis.shop.data.remote.response.productbaru.ProductItems
import com.alexis.shop.domain.model.product.category.ProductCategoryNewItem
import com.alexis.shop.domain.model.product.category.ProductCategoryNewModel
import com.alexis.shop.domain.model.product.category.SubCategoryModel
import com.alexis.shop.domain.model.product.modelbaru.ImagesModel
import com.alexis.shop.domain.model.product.modelbaru.ProductBaruModel
import com.alexis.shop.domain.repository.product.IProductCategoryRepository
import com.alexis.shop.utils.orZero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductCategoryRepository @Inject constructor(
    private val remoteDataSource: ProductCategoryRemoteDataSource
) : IProductCategoryRepository {

    override fun getAllProductCategory(): Flow<Resource<ProductCategoryNewModel>> {
        return flow<Resource<ProductCategoryNewModel>> {
            emit(Resource.Loading())
            when(val apiResponses = remoteDataSource.getAllProductCategory().first()) {
                is ApiResponse.Success -> emit(
                    Resource.Success(
                        generateCategoryList(apiResponses.data.data.items)
                    )
                )
                is ApiResponse.Empty -> listOf<ProductCategoryNewModel>()
                is ApiResponse.Error -> emit(Resource.Error(apiResponses.errorMessage))
            }
        }
    }

    override fun getCategoryById(id: String): Flow<Resource<List<ProductBaruModel>>> {
        return flow<Resource<List<ProductBaruModel>>> {
            emit(Resource.Loading())
            when(val apiResource = remoteDataSource.getCategoryById(id).first()){
                is ApiResponse.Success -> emit(Resource.Success(generateListProducts(apiResource.data.data?.product)))
                is ApiResponse.Empty -> listOf<ProductBaruModel>()
                is ApiResponse.Error -> emit(Resource.Error(apiResource.errorMessage))
            }
        }
    }


    private fun generateCategoryList(data: List<ProductCategoryItem?>?): ProductCategoryNewModel {
       return if (!data.isNullOrEmpty()) {
           ProductCategoryNewModel(
               data = data.map {
                   ProductCategoryNewItem(
                       category = it?.category.orEmpty(),
                       icon = it?.icon.orEmpty(),
                       sub_category = generateSubCategory(it?.sub_category),
                       isExpandable = it!!.isExpandable
                   )
               }
           )
       }else ProductCategoryNewModel()
    }

    private fun generateSubCategory(data: List<SubCategoryItem>?) : List<SubCategoryModel> {
        val generateValue = ArrayList<SubCategoryModel>()
        data?.forEach {
            generateValue.add(
                SubCategoryModel(
                    id = it.id,
                    merchandise_name = it.merchandise_name
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