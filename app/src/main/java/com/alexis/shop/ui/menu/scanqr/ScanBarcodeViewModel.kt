package com.alexis.shop.ui.menu.scanqr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alexis.shop.domain.usecase.product.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScanBarcodeViewModel @Inject constructor(
    private val productUseCase : ProductUseCase
) : ViewModel(){

    fun getProductByBarcode(barcode : String) = productUseCase.getProductByBarcode(barcode).asLiveData()
}