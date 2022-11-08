package com.alexis.shop.ui.menu.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.store_location.StoreLocationByNameModel
import com.alexis.shop.utils.OnClickItem
import com.alexis.shop.utils.visible

class DetailLocationAdapter (private val listener: OnClickItem) : RecyclerView.Adapter<DetailLocationAdapter.DetailLocationViewHolder>() {

    private var locationList = ArrayList<StoreLocationByNameModel>()

    fun setData(data: ArrayList<StoreLocationByNameModel>) {
        locationList.clear()
        locationList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailLocationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DetailLocationViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: DetailLocationViewHolder, position: Int) {
        val item : StoreLocationByNameModel = locationList[position]
        Log.d("ALAMATT", "$item")
        holder.bind(item)
        holder.itemView.setOnClickListener { listener.onClick(item) }
    }

    override fun getItemCount(): Int = locationList.size

    class DetailLocationViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.item_detail_storelocation, parent, false)) {
        private var detail_mode: ConstraintLayout = itemView.findViewById(R.id.detail_mode)
        private var txt_1: TextView = itemView.findViewById(R.id.txt_1)
        private var txt_2: TextView = itemView.findViewById(R.id.txt_2)
        private var txt_3: TextView = itemView.findViewById(R.id.txt_3)
        private var txt_4: TextView = itemView.findViewById(R.id.txt_4)

        fun bind(item: StoreLocationByNameModel) {
            detail_mode.visible()
            txt_1.text = item.province
            txt_2.text = item.city
            txt_3.text = item.phoneNumber
            val openTime = "${item.openTime} - ${item.closeTime}"
            txt_4.text = openTime
        }
    }

}