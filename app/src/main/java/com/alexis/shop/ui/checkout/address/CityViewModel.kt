package com.alexis.shop.ui.checkout.address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alexis.shop.domain.usecase.city.CityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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