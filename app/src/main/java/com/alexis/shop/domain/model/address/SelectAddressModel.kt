package com.alexis.shop.domain.model.address

class SelectAddressModel constructor(private var data: String, private var choosed: Boolean){

    fun getData(): String {
        return data
    }

    fun setData(data: String){
        this.data = data
    }

    fun getChoosed(): Boolean{
        return choosed
    }

    fun setChoosed(choosed: Boolean){
        this.choosed = choosed
    }
}