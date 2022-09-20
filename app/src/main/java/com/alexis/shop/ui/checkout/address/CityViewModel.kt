package com.alexis.shop.ui.checkout.address

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.model.kota.CityResponse
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.domain.model.city.AllCityModel
import com.alexis.shop.domain.usecase.city.CityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class CityViewModel @Inject constructor(
    private val cityUseCase: CityUseCase
) : ViewModel(){
  //  val searchCity : MutableLiveData<Resource<CityResponse>> = MutableLiveData()

    fun getCitySearch(token : String, name : String) = cityUseCase.getAllVoucher(token, name).asLiveData()

//    fun searchCity(token : String, name : String) = viewModelScope.launch {
//        searchCity.postValue(Resource.Loading())
//        val response = cityUseCase.getAllVoucher(token, name)
//        searchCity.postValue(handleSearchCity(response))
//    }
//    private fun handleSearchCity(response : Flow<Resource<AllCityModel>>) : Resource<CityResponse> {
//        if (response.isSuccessful) {
//            response.body()?.let { result ->
//                return Resource.Success(result)
//            }
//        }
//        return Resource.Error(response.message())
//    }
}