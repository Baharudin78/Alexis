package com.alexis.shop.data.remote.response.product

import com.alexis.shop.data.remote.response.barcode.ProductSubCategoryItem
import com.google.gson.annotations.SerializedName

data class ProductsGetByIdResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: ProductsGetByIdData? = null,

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ProductsGetByIdData(

	@field:SerializedName("item")
	val product: ProductsGetByIdItem? = null
)

data class ProductsGetByIdItem(

	@field:SerializedName("id")
	val productId: Int? = null,

	@field:SerializedName("barcode")
	val barcode : String? = null,

	@field:SerializedName("stock_keeping_unit")
	val stockKeepingUnit : String? = null,

	@field:SerializedName("item_code")
	val itemCode : String? = null,

	@field:SerializedName("name")
	val productName: String? = null,

	@field:SerializedName("product_subcategory_id")
	val productSubcategoryId : String? = null,

	@field:SerializedName("stock")
	val stock: Int? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("weight")
	val weight: Int? = null,

	@field:SerializedName("style_code")
	val styleCode : String? = null,

	@field:SerializedName("product_material_id")
	val productMaterialId : String? = null,

	@field:SerializedName("color_code")
	val colorCode : String? = null,

	@field:SerializedName("product_size_id")
	val productSizeId : String? = null,

	@field:SerializedName("packaging_id")
	val packagingId : String? = null,

	@field:SerializedName("status")
	val status : String? = null,

	@field:SerializedName("store_location_id")
	val storeLocationId : Int? = null,

	@field:SerializedName("user_id")
	val userId : Int? = null,

	@field:SerializedName("product_image")
	val images: List<ProductsGetByIdImagesItem>? = null,

	@field:SerializedName("product_material")
	val productMaterialItem : ProductsGetByIdMaterialItem? = null,

	@field:SerializedName("product_size")
	val productSizeItem : ProductsGetByIdSizeItem? = null,

	@field:SerializedName("product_subcategory")
	val productSubCategory : ProductsSubCategoryItem? = null,

	@field:SerializedName("exclusive_offer")
	val exclusiveOffer : ExclusiveOfferItem? = null
)

data class ProductsSubCategoryItem(
	@field:SerializedName("id")
	val id : Int? = null,
	@field:SerializedName("sequence")
	val sequence : Int? = null,
	@field:SerializedName("name_in_id")
	val nameInId : String? = null,
	@field:SerializedName("name_in_eng")
	val nameInEng : String? = null,
	@field:SerializedName("selling_price")
	val sellingPrice : Int? = null,
	@field:SerializedName("market_price")
	val marketPrice : Int? = null,
	@field:SerializedName("product_category_id")
	val productCategoryId : Int? = null,
	@field:SerializedName("max_listing_period")
	val maxListingPeriod : Int? = null,
	@field:SerializedName("target_daily_listing_qty")
	val targetDailyListingQty : Int? = null,
	@field:SerializedName("min_listing")
	val minListing : Int? = null,
	@field:SerializedName("style_in_homepage")
	val styleInHomepage : Int? = null,
	@field:SerializedName("size_in_tray")
	val sizeInTray : Int? = null,
	@field:SerializedName("product_size_id")
	val productSizeId : Int? = null,
	@field:SerializedName("product_material_id")
	val productMaterialId : Int? = null,
	@field:SerializedName("package_id")
	val packageId : Int? = null,
	@field:SerializedName("package_real_weight")
	val packageRealWeight : Int? = null,
	@field:SerializedName("package_volume_weight")
	val packageVolumeWeight : Int? = null
)

data class ProductsGetByIdSizeItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("selection")
	val selection : String? = null,

	@field:SerializedName("product_size_code")
	val productSizeCode : String? = null
)

data class ProductsGetByIdMaterialItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
data class ProductsGetByIdImagesItem(

	@field:SerializedName("id")
	val id : String? = null,

	@field:SerializedName("product_item_code")
	val productItemCode : String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("product_list_display")
	val productListDisplay : String? = null,

	@field:SerializedName("product_detail_display")
	val productDetailDisplay : Int? = null,

	@field:SerializedName("bag_wishlist_order_display")
	val bagWishlistOrderDisplay : Int? = null
)


data class ExclusiveOfferItem(
	@field:SerializedName("id")
	val id : Int? = null,
	@field:SerializedName("product_item_code")
	val productTtemCode : String? = null,
	@field:SerializedName("aging")
	val aging : Int? = null,
	@field:SerializedName("redemption_point")
	val redemptionPoint : Int? = null,
	@field:SerializedName("registration_date")
	val registrationDate : String? = null
)
