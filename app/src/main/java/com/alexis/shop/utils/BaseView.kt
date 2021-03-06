package com.alexis.shop.utils

interface OnClickItem{
    fun onClick(item : Any)
}

interface OnReturnClickItem{
    fun onClick(item : Any)
    fun onLongClick(item : Any, text: String)
}

interface OnWishlistClickItem{
    fun onDeleteItem(item : Any)
    fun onMove2BasketClick(item : Any)
}

interface OnShoppingBagClickItem{
    fun onDeleteItem(item : Any)
    fun onMove2WishList(item : Any)
    fun onEditItem(item : Any)
}
