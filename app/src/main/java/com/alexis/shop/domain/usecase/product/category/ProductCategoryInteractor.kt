package com.alexis.shop.domain.usecase.product.category

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.product.category.ProductCategoryModel
import com.alexis.shop.domain.model.product.category.ProductCategoryModelList
import com.alexis.shop.domain.model.product.category.subcategory.SubCategoryModel
import com.alexis.shop.domain.repository.product.IProductCategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductCategoryInteractor @Inject constructor(private val repository: IProductCategoryRepository):
    ProductCategoryUseCase {
    override  fun getAllProductCategory(): Flow<Resource<ProductCategoryModelList>> {
        return repository.getAllProductCategory()
    }
    override  fun getSubProductCategory(name : String): Flow<Resource<List<SubCategoryModel>>> {
        return repository.getSubCategoryProduct(name)
    }
}