package com.alexis.shop.data.remote.response.storelocation

import com.alexis.shop.ui.detail.adapter.viewholder.LocationTypeVH
import com.dizcoding.mylibrv.BaseItemModel
import com.dizcoding.mylibrv.BaseItemTypeFactory
import com.google.gson.annotations.SerializedName

data class AllStoreLocationResponse(
	@SerializedName("data")
	val data: DataStoreLocationResponse? = null,
)

data class DataStoreLocationResponse(
	@SerializedName("message")
	val message: String? = null,

	@SerializedName("items")
	val storeLocation: List<AllStoreItemResponse>? = null
)

data class AllStoreItemResponse(
	@SerializedName("id")
	val id : Int? = null,
	@SerializedName("store_area")
	val storeArea : Int? = null,
	@SerializedName("province")
	val province: String? = null,
	@SerializedName("name")
	val name : String? = null,
	@SerializedName("city")
	val city : String? = null,
	@SerializedName("phone_number")
	val phoneNumber : String? = null,
	@SerializedName("open_time")
	val openTime : String? = null,
	@SerializedName("close_time")
	val closeTime : String? = null,
	@SerializedName("latitude")
	val latitude : String? = null,
	@SerializedName("longitude")
	val longitude : String? = null,
	@SerializedName("image_url")
	val imageUrl : String? = null

) : BaseItemModel() {
	override fun type(typeFactoryBase: BaseItemTypeFactory): Int {
		typeVH = LocationTypeVH.LAYOUT
		return typeFactoryBase.type(this)
	}
}
