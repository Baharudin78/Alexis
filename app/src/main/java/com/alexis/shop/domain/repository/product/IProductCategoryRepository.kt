package com.alexis.shop.domain.repository.product

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.product.category.ProductCategoryModel
import com.alexis.shop.domain.model.product.category.ProductCategoryModelList
import com.alexis.shop.domain.model.product.category.subcategory.SubCategoryModel
import kotlinx.coroutines.flow.Flow

interface IProductCategoryRepository {
     fun getAllProductCategory(): Flow<Resource<ProductCategoryModelList>>
     fun getSubCategoryProduct(name : String) : Flow<Resource<List<SubCategoryModel>>>
}