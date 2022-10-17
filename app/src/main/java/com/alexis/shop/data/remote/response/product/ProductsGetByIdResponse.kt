package com.alexis.shop.data.remote.response.product

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

data class ProductsGetByIdImagesItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)

data class ProductsGetByIdData(

	@field:SerializedName("item")
	val product: ProductsGetByIdItem? = null
)

data class ProductsGetByIdItem(

	@field:SerializedName("product_image")
	val images: List<ProductsGetByIdImagesItem>? = null,

	@field:SerializedName("category_name")
	val categoryName: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("product_size")
	val size: List<ProductsGetByIdSizeItem>? = null,

	@field:SerializedName("material")
	val material: List<ProductsGetByIdMaterialItem>? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("id")
	val productId: Int? = null,

	@field:SerializedName("weight")
	val weight: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

//	@field:SerializedName("id")
//	val id: Int? = null,

	@field:SerializedName("stock")
	val stock: Int? = null,

	@field:SerializedName("name")
	val productName: String? = null,

	@field:SerializedName("exclusive_offer")
	val exclusiveOffer : ExclusiveOfferItem? = null
)

data class ProductsGetByIdSizeItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class ProductsGetByIdMaterialItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
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
