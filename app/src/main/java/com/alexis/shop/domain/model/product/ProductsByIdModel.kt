package com.alexis.shop.domain.model.product

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


@Parcelize
data class ProductsByIdModel(
    val id: Int? = null,
    val barcode : String? = null,
    val stockKeepingUnit : String? = null,
    val itemCode : String? = null,
    val productName: String? = null,
    val productSubcategoryId : String? = null,
    val stock : Int? = null,
    val price : Int? = null,
    val weight : Int? = null,
    val styleCode : String? = null,
    val productMaterialId : String? = null,
    val colorCode : String? = null,
    val productSizeId : String? = null,
    val packagingId : String? = null,
    val status : String? = null,
    val storeLocationId : Int? = null,
    val userId : Int? = null,
    val images: @RawValue MutableList<ProductsGetByIdImagesModel> = mutableListOf(),
    val productMaterial : ProductsGetByIdMaterialModel? = null,
    val productSize : ProductsGetByIdSizeModel? = null,
    val subProduct : SubProductItemModel? = null,
    val productSizeList: @RawValue MutableList<ProductsGetByIdSizeModel> = mutableListOf(),
    val exclusiveOffer: ProductExclusiveOffer? = null,
) : Parcelable

@Parcelize
data class ProductsGetByIdImagesModel(
    val image: String = "",
    val type: String = ""
) : Parcelable

@Parcelize
data class SubProductItemModel(
    val id : Int? = null,
    val sequence : Int? = null,
    val nameInId : String? = null,
    val nameInEng : String? = null,
    val sellingPrice : Int? = null,
    val marketPrice : Int? = null,
    val productCategoryId : Int? = null,
    val maxListingPeriod : Int? = null,
    val targetDailyListingQty : Int? = null,
    val minListing : Int? = null,
    val styleInHomepage : Int? = null,
    val sizeInTray : Int? = null,
    val productSizeId : Int? = null,
    val productMaterialId : Int? = null,
    val packageId : Int? = null,
    val packageRealWeight : Int? = null,
    val packageVolumeWeight : Int? = null
) : Parcelable

@Parcelize
data class ProductsGetByIdSizeModel(
    val name: String = "",
    val id: String = "",
    var isSelected: Boolean = false
) : Parcelable

@Parcelize
data class ProductsGetByIdMaterialModel(
    val name: String = "",
    val id: String = ""
) : Parcelable

@Parcelize
data class ProductExclusiveOffer(
    val id : Int = 0,
    val productItemCode : String = "",
    val redemptionPoint : Int = 0,
    val aging : Int = 0,
    val registrationDate :String = ""
) : Parcelable
