package com.alexis.shop.domain.model.faq

class FAQModel constructor(private var data: String, private var choosed: Boolean){

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