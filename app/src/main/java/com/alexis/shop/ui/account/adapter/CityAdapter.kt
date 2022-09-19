package com.alexis.shop.ui.account.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.city.CityItemModel
import com.alexis.shop.utils.OnClickItem

class CityAdapter(
    private val context : Context,
    private val listener : OnClickItem
    ) : RecyclerView.Adapter<CityAdapter.CityHolder>() {

    private var itemList = ArrayList<CityItemModel>()
    fun setData(data : ArrayList<CityItemModel>) {
        itemList.clear()
        itemList = data
        notifyDataSetChanged()
    }

    class CityHolder(inflater : LayoutInflater, parent : ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.item_city, parent, false)) {
                var nama : TextView = itemView.findViewById(R.id.tv_nama_kel)
        fun bindItem(item : CityItemModel) {
            nama.text = item.fullName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CityHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        val item : CityItemModel = itemList[position]
        holder.bindItem(item)

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}