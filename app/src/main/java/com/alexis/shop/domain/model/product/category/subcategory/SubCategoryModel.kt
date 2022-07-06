package com.alexis.shop.domain.model.product.category.subcategory

data class SubCategoryModel(
    val id: Int,
    val marketPrice: Int,
    val maxListingPeriod: Int,
    val minListing: Int,
    val nameInEng: String,
    val nameInEngSelection: String,
    val nameInId: String,
    val nameInIdSelection: String,
    val packageId: Int,
    val packageRealWeight: Int,
    val packageVolumeWeight: Int,
    val productCategoryId: Int,
    val productMaterialId: Int,
    val productSizeId: Int,
    val sellingPrice: Int,
    val sequence: Int,
    val sizeInTray: Int,
    val styleInHomepage: Int,
    val targetDailyListingQty: Int,
    val totalProduct: String
)