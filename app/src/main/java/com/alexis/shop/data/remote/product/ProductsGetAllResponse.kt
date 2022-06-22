package com.alexis.shop.data.remote.product

import com.google.gson.annotations.SerializedName

data class ProductsResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: ProductsData? = null,

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ProductsData(

	@field:SerializedName("products")
	val products: List<ProductsItem>? = null
)

data class ProductsItem(

	@field:SerializedName("thumbnail")
	val thumbnail: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("product_id")
	val id: Int? = null,

	@field:SerializedName("indonesia_name")
	val indonesiaName: String? = null,

	@field:SerializedName("stock")
	val stock: Int? = null,

	@field:SerializedName("barcode")
	val barcode: String? = null,

	@field:SerializedName("english_name")
	val englishName: String? = null,

	@field:SerializedName("image_type")
	val imageType: String? = null
)
