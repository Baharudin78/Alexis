package com.alexis.shop.ui.menu.adapter

import android.content.Context
import android.print.PrintDocumentAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.utils.OnClickItem

class StoreHomeAdapter(private val context : Context,
                       private val listener : OnClickItem
    ) : RecyclerView.Adapter<StoreHomeAdapter.StoreHolder>(){

        inner class StoreHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate())
}