package com.alexis.shop.domain.repository.product

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.product.category.ProductCategoryModel
import com.alexis.shop.domain.model.product.category.subcategory.SubCategoryModel

interface IProductCategoryRepository {
    suspend fun getAllProductCategory(): Resource<List<ProductCategoryModel>>
    suspend fun getSubCategoryProduct(name : String) : Resource<List<SubCategoryModel>>
}