package com.alexis.shop.data.remote.response.storelocation

import com.alexis.shop.ui.detail.adapter.viewholder.LocationTypeVH
import com.dizcoding.mylibrv.BaseItemModel
import com.dizcoding.mylibrv.BaseItemTypeFactory
import com.google.gson.annotations.SerializedName

data class AllStoreLocationResponse(
	@field:SerializedName("data")
	val data: DataStoreLocationResponse? = null,
)

data class DataStoreLocationResponse(
	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("items")
	val storeLocation: List<AllStoreItemResponse>? = null
)

data class AllStoreItemResponse(
	@field:SerializedName("id")
	val id : Int? = null,
	@field:SerializedName("province")
	val province: String? = null,
	@field:SerializedName("name")
	val name : String? = null,
	@field:SerializedName("city")
	val city : String? = null,
	@field:SerializedName("phone_number")
	val phoneNumber : String? = null,
	@field:SerializedName("open_time")
	val openTime : String? = null,
	@field:SerializedName("close_time")
	val closeTime : String? = null
) : BaseItemModel() {
	override fun type(typeFactoryBase: BaseItemTypeFactory): Int {
		typeVH = LocationTypeVH.LAYOUT
		return typeFactoryBase.type(this)
	}

}
