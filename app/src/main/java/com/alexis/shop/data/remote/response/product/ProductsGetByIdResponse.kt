package com.alexis.shop.data.remote.response.product

import com.alexis.shop.data.remote.response.barcode.ProductSubCategoryItem
import com.google.gson.annotations.SerializedName

data class ProductsGetByIdResponse(

	@SerializedName("code")
	val code: Int? = null,

	@SerializedName("data")
	val data: ProductsGetByIdData? = null,

	@SerializedName("error")
	val error: String? = null,

	@SerializedName("status")
	val status: String? = null
)

data class ProductsGetByIdData(

	@SerializedName("item")
	val product: ProductsGetByIdItem? = null
)

data class ProductsGetByIdItem(

	@SerializedName("id")
	val productId: Int? = null,

	@SerializedName("barcode")
	val barcode : String? = null,

	@SerializedName("stock_keeping_unit")
	val stockKeepingUnit : String? = null,

	@SerializedName("item_code")
	val itemCode : String? = null,

	@SerializedName("name")
	val productName: String? = null,

	@SerializedName("product_subcategory_id")
	val productSubcategoryId : String? = null,

	@SerializedName("stock")
	val stock: Int? = null,

	@SerializedName("price")
	val price: Int? = null,

	@SerializedName("weight")
	val weight: Int? = null,

	@SerializedName("style_code")
	val styleCode : String? = null,

	@SerializedName("product_material_id")
	val productMaterialId : String? = null,

	@SerializedName("color_code")
	val colorCode : String? = null,

	@SerializedName("product_size_id")
	val productSizeId : String? = null,

	@SerializedName("packaging_id")
	val packagingId : String? = null,

	@SerializedName("status")
	val status : String? = null,

	@SerializedName("store_location_id")
	val storeLocationId : Int? = null,

	@SerializedName("user_id")
	val userId : Int? = null,

	@SerializedName("product_image")
	val images: List<ProductsGetByIdImagesItem>? = null,

	@SerializedName("product_material")
	val productMaterialItem : ProductsGetByIdMaterialItem? = null,

	@SerializedName("product_size")
	val productSizeItem : ProductsGetByIdSizeItem? = null,

	@SerializedName("product_subcategory")
	val productSubCategory : ProductsSubCategoryItem? = null,

	@SerializedName("exclusive_offer")
	val exclusiveOffer : ExclusiveOfferItem? = null
)

data class ProductsSubCategoryItem(
	@SerializedName("id")
	val id : Int? = null,
	@SerializedName("sequence")
	val sequence : Int? = null,
	@SerializedName("name_in_id")
	val nameInId : String? = null,
	@SerializedName("name_in_eng")
	val nameInEng : String? = null,
	@SerializedName("selling_price")
	val sellingPrice : Int? = null,
	@SerializedName("market_price")
	val marketPrice : Int? = null,
	@SerializedName("product_category_id")
	val productCategoryId : Int? = null,
	@SerializedName("max_listing_period")
	val maxListingPeriod : Int? = null,
	@SerializedName("target_daily_listing_qty")
	val targetDailyListingQty : Int? = null,
	@SerializedName("min_listing")
	val minListing : Int? = null,
	@SerializedName("style_in_homepage")
	val styleInHomepage : Int? = null,
	@SerializedName("size_in_tray")
	val sizeInTray : Int? = null,
	@SerializedName("product_size_id")
	val productSizeId : Int? = null,
	@SerializedName("product_material_id")
	val productMaterialId : Int? = null,
	@SerializedName("package_id")
	val packageId : Int? = null,
	@SerializedName("package_real_weight")
	val packageRealWeight : Int? = null,
	@SerializedName("package_volume_weight")
	val packageVolumeWeight : Int? = null
)

data class ProductsGetByIdSizeItem(

	@SerializedName("name")
	val name: String? = null,

	@SerializedName("id")
	val id: String? = null,

	@SerializedName("selection")
	val selection : String? = null,

	@SerializedName("product_size_code")
	val productSizeCode : String? = null
)

data class ProductsGetByIdMaterialItem(

	@SerializedName("name")
	val name: String? = null,

	@SerializedName("id")
	val id: String? = null
)
data class ProductsGetByIdImagesItem(

	@SerializedName("id")
	val id : String? = null,

	@SerializedName("product_item_code")
	val productItemCode : String? = null,

	@SerializedName("image_url")
	val imageUrl: String? = null,

	@SerializedName("type")
	val type: String? = null,

	@SerializedName("product_list_display")
	val productListDisplay : String? = null,

	@SerializedName("product_detail_display")
	val productDetailDisplay : Int? = null,

	@SerializedName("bag_wishlist_order_display")
	val bagWishlistOrderDisplay : Int? = null
)


data class ExclusiveOfferItem(
	@SerializedName("id")
	val id : Int? = null,
	@SerializedName("product_item_code")
	val productTtemCode : String? = null,
	@SerializedName("aging")
	val aging : Int? = null,
	@SerializedName("redemption_point")
	val redemptionPoint : Int? = null,
	@SerializedName("registration_date")
	val registrationDate : String? = null
)
