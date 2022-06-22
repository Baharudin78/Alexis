package com.alexis.shop.data.remote.product

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

	@field:SerializedName("product")
	val product: ProductsGetByIdItem? = null
)

data class ProductsGetByIdItem(

	@field:SerializedName("images")
	val images: List<ProductsGetByIdImagesItem>? = null,

	@field:SerializedName("category_name")
	val categoryName: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("size")
	val size: List<ProductsGetByIdSizeItem>? = null,

	@field:SerializedName("material")
	val material: List<ProductsGetByIdMaterialItem>? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("product_id")
	val productId: Int? = null,

	@field:SerializedName("weight")
	val weight: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("stock")
	val stock: Int? = null,

	@field:SerializedName("product_name")
	val productName: String? = null
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
