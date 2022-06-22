package com.alexis.shop.domain.model.product

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Uwais Alqadri on July 06, 2021
 */
@Parcelize
data class Product(
	val id: Int,
	val image: ImageModel,
	val name: String,
	val price: String,
): Parcelable
