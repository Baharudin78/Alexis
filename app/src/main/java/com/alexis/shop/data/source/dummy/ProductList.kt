package com.alexis.shop.data.source.dummy

import com.alexis.shop.domain.model.product.ImageModel
import com.alexis.shop.domain.model.product.Product

/**
 * Created by Uwais Alqadri on July 07, 2021
 */
fun getListProduct(): ArrayList<Product> {
	return arrayListOf(
		Product(
			1,
			ImageModel("https://cdn.tobi.com/product_images/sm/2/grey-ayda-cowl-neck-sweater-dress.jpg", false),
			"Sweater",
			"90.000"
		),
		Product(
			2,
			ImageModel("http://api.myalexis.xyz:3001/uploads/product_image/234234BA-AYAUB-B1-S1.jpg", false),
			"Cardigan",
			"80.000"
		),
		Product(
			3,
			ImageModel("http://api.myalexis.xyz:3001/uploads/product_image/234234BB-AYAUB-B1-S1.jpg", false),
			"Jacket Jeans",
			"100.000"
		),
		Product(
			4,
			ImageModel("http://api.myalexis.xyz:3001/uploads/product_image/234234BC-AYAUB-B1-S1.jpg", false),
			"Cool Jackets",
			"145.000"
		),
		Product(
			5,
			ImageModel("http://api.myalexis.xyz:3001/uploads/product_image/234234BD-AYAUB-B1-S1.jpg", false),
			"High Heels",
			"150.000"
		),
//		Product(
//			5,
//			ImageModel("https://lh3.googleusercontent.com/VtJraf19hy9HWoFyQc54V6mwKh19MjWM4QMbYXYZFXZnirWFZQfxS9Zju_OrLfBfZzsGThxEBGcpTC1YuEOWLGTPO8wQvPaW6kjbRcsKaRfWH2V-uQxcLOArouxrtk1s5Bp6gYM5iWsedRaCAUsylZZvE-l69tfFfA4KoQtHlYemJeWh76BbXN8jjghM2nBDrCrtWDmV0m-aSgloKRn1JlVJjaKrAvMhOgonxg8kqnhMUYMlmZVhlFmX6_MyT3Yrcejr-nucHb_rCvkCU3mvhjYmiXDnLNdBlBjoOEa6ZcGnv6mgxqmJXzPVVc_cEPZcrWT70df33c5m8M7bM6S0SnGRrrkKJSQtOAZUjDZWn9vxvLBsO0OljsayEkzJHnrPBYSz-GUd5NB8MrdMgcDgeHGs1q5xsbdSJw_pH-7j6717yFIpoK8OR_zKw9-gG7Rqo-S2Gm7M5qBBMwR7_OMFklFBgzy-of-kfdh-0F4z-LvEPcCBhsZ0dNjWktiIQC3xsnEGe0-CXMDF7B8NieGAQvJ8KM7EFVqeb_8Ggp-aa180phGg_M6lJTIz9X2WRP4foNPKn-v7o_6lJWeQ3nfN9_rKRc72Y5_3kD91sYYf18Q4rux0LuiwCGyjD-0t7aiHPblhcCLawBzHYmxKhjOgk6ecy4aWkEnC_K2VAiVpK1bJEJOG1ej22d00EKppoaCJB-uH-qu8pE0OFDt2egtibM4w=w328-h363-no?authuser=0", false),
//			"",
//			""
//		)
	)
}