package com.alexis.shop.ui.menu.sizefilter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.sizefilter.SizeFilterModel
import com.alexis.shop.domain.usecase.sizefilter.SizeFilterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SizeFilterViewModel @Inject constructor(private val useCase: SizeFilterUseCase): ViewModel() {
    fun getSizeFilter(): LiveData<Resource<List<SizeFilterModel>>> {
        return useCase.getSizeFilter().asLiveData()
    }

    fun postSizeFilter(sizeId : List<Int>) = useCase.postSizeFilter(sizeId).asLiveData()

}