package com.alexis.shop.data.source.dummy

import com.alexis.shop.domain.model.order.TrackingModel

/**
 * Created by Uwais Alqadri on July 06, 2021
 */
object TrackingList {
	fun getTrackingList(): List<TrackingModel> {
		return arrayListOf(
			TrackingModel("03Nov 12:10", "delivered, received by Nawi [Jakarta]"),
			TrackingModel("03Nov 08:40", "with delivery courier [Jakarta]"),
			TrackingModel("03Nov 04:14", "received at inbound station [Jakarta]"),
			TrackingModel("03Nov 00:06", "forwarded to destination [Jakarta]"),
			TrackingModel("02Nov 23:14", "received at warehouse [Jakarta]"),
			TrackingModel("02Nov 10:24", "processed at sorting center [Cilacap"),
			TrackingModel("02Nov 08:28", "received at sorting center [Cilacap]"),
			TrackingModel("01Nov 14:13", "on process"),
			TrackingModel("01Nov 12:02", "received by JNE at [Cilacap]"),
			TrackingModel("01Nov 11:50", "picked at warehouse [Cilacap]"),
			TrackingModel("01Nov 11:09", "packed at warehouse [Cilacap]"),
			TrackingModel("01Nov 09:35", "received payment"),
		)
	}
}

//03-11-2020 12:10 [JAKARTA]DELIVERED TO [nawi | 03-11-2020 12:10 | JAKARTA ] 03-11-2020 08:48 WITH DELIVERY COURIER [JAKARTA]
//03-11-2020 04:14 RECEIVED AT INBOUND STATION [JAKARTA, ANGKE(DESP)] 03-11-2020 00:06 SHIPMENT FORWARDED TO DESTINATION [JAKARTA, ANGKE(DESP)] 02-11-2020 23:14 RECEIVED AT WAREHOUSE [JAKARTA]
//02-11-2020 10:24 PROCESSED AT SORTING CENTER [CILACAP]
//02-11-2020 08:28 RECEIVED AT SORTING CENTER [CILACAP]
//01-11-2020 14:13 ON PROCESS
//01-11-2020 14:13 SHIPMENT RECEIVED BY JNE COUNTER OFFICER AT [CILACAP] (JNE:8826412012515518) 01-11-2020 11:01 PACKED THE PRODUCT AT ALEXIS WAREHOUSE
//01-11-2020 09:35 RECEIVED ONLINE ORDER














