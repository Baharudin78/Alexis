package com.alexis.shop.ui.menu.adapter

import android.content.Context
import android.print.PrintDocumentAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.store_location.AllStoreItemModel
import com.alexis.shop.utils.OnClickItem

class StoreHomeAdapter(private val context : Context,
                     //  private val listener : OnClickItem
    ) : RecyclerView.Adapter<StoreHomeAdapter.StoreHolder>(){

    private var storeList = ArrayList<AllStoreItemModel>()
    class StoreHolder(inflater: LayoutInflater, parent: ViewGroup)
        : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_store_home, parent, false)) {
        var tvTitle : TextView = itemView.findViewById(R.id.tv_nama)
        var tvLokasi : TextView = itemView.findViewById(R.id.tv_lokasi)

        fun bind(item : AllStoreItemModel) {
            tvTitle.text = item.name
            tvLokasi.text = item.city
        }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StoreHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: StoreHolder, position: Int) {
        val item : AllStoreItemModel = storeList[position]
        holder.bind(item)
     //   holder.itemView.setOnClickListener { listener.onClick(item) }
    }

    override fun getItemCount(): Int {
        return storeList.size
    }

}