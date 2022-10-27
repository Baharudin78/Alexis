package com.alexis.shop.data.remote.response.shoppingbag

import com.alexis.shop.data.remote.response.product.ProductsGetByIdItem
import com.google.gson.annotations.SerializedName

data class ShoppingBagResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: DataCarts? = null,

	@field:SerializedName("error")
	val error: Any? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataCarts(

	@field:SerializedName("items")
	val carts: List<CartsItem>? = null
)

data class CartsItem(

	@field:SerializedName("id")
	val id : Int? = null,
	@field:SerializedName("customer_id")
	val customerId : Int? = null,
	@field:SerializedName("product_item_code")
	val productItemCode : String? = null,
	@field:SerializedName("product_size_id")
	val productSizeId : String? = null,
	@field:SerializedName("unit")
	val unit : String? = null,
	@field:SerializedName("price")
	val price : Int? = null,
	@field:SerializedName("final_price")
	val finalPrice : Int? = null,
	@field:SerializedName("product")
	val product : ProductsGetByIdItem? = null
)


