package com.alexis.shop.domain.usecase.product.category

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.product.category.ProductCategoryModel
import com.alexis.shop.domain.model.product.category.subcategory.SubCategoryModel
import com.alexis.shop.domain.repository.product.IProductCategoryRepository
import javax.inject.Inject

class ProductCategoryInteractor @Inject constructor(private val repository: IProductCategoryRepository):
    ProductCategoryUseCase {
    override suspend fun getAllProductCategory(): Resource<List<ProductCategoryModel>> {
        return repository.getAllProductCategory()
    }
    override suspend fun getSubProductCategory(name : String): Resource<List<SubCategoryModel>> {
        return repository.getSubCategoryProduct(name)
    }
}