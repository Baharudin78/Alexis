package com.alexis.shop.data.source.network

import com.alexis.shop.domain.model.product.category.ProductCategoryModel
import java.lang.reflect.Array

fun getProductCategory(category : List<ProductCategoryModel>) : ArrayList<ProductCategoryModel> {
    val productCategory = ArrayList<ProductCategoryModel>()
    category.map {
        it.category
    }
    return productCategory
}
