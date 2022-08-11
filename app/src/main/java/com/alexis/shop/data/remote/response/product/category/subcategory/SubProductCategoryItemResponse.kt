package com.alexis.shop.data.remote.response.product.category.subcategory

import com.google.gson.annotations.SerializedName

data class SubProductCategoryItemResponse(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("market_price")
    val marketPrice: Int,
    @field:SerializedName("max_listing_period")
    val maxListingPeriod: Int,
    @field:SerializedName("min_listing")
    val minListing: Int,
    @field:SerializedName("name_in_eng")
    val nameInEng: String,
    @field:SerializedName("name_in_eng_selection")
    val nameInEngSelection: String,
    @field:SerializedName("name_in_id")
    val nameInId: String,
    @field:SerializedName("name_in_id_selection")
    val nameInIdSelection: String,
    @field:SerializedName("package_id")
    val packageId: Int,
    @field:SerializedName("package_real_weight")
    val packageRealWeight: Int,
    @field:SerializedName("package_volume_weight")
    val packageVolumeWeight: Int,
    @field:SerializedName("product_category_id")
    val productCategoryId: Int,
    @field:SerializedName("product_material_id")
    val product_materialId: Int,
    @field:SerializedName("product_size_id")
    val productSizeId: Int,
    @field:SerializedName("selling_price")
    val sellingPrice: Int,
    @field:SerializedName("sequence")
    val sequence: Int,
    @field:SerializedName("size_in_tray")
    val sizeInTray: Int,
    @field:SerializedName("style_in_homepage")
    val styleInHomepage: Int,
    @field:SerializedName("target_daily_listing_qty")
    val targetDailyListingQty: Int,
    @field:SerializedName("total_product")
    val totalProduct: String
)