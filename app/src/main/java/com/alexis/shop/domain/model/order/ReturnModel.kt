package com.alexis.shop.domain.model.order

class ReturnModel constructor(private var item: String,
                              private var scaled: Boolean,
                              private var selected: Boolean,
                              private var reason: String){
    fun getItem(): String {
        return item
    }

    fun setItem(item: String){
        this.item = item
    }

    fun getScaled(): Boolean{
        return scaled
    }

    fun setScaled(scaled: Boolean){
        this.scaled = scaled
    }

    fun getSelected(): Boolean{
        return selected
    }

    fun setSelected(selected: Boolean){
        this.selected = selected
    }

    fun getReason(): String{
        return reason
    }

    fun setReason(reason: String){
        this.reason = reason
    }
}
