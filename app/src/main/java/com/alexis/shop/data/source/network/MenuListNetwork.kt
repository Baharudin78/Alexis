package com.alexis.shop.data.source.network

import com.alexis.shop.domain.model.product.category.ProductCategoryNewModel
import java.lang.reflect.Array

fun getProductCategory(category : List<ProductCategoryNewModel>) : ArrayList<ProductCategoryNewModel> {
    val productCategory = ArrayList<ProductCategoryNewModel>()
    category.map {
//        it.data.mao
    }
    return productCategory
}
