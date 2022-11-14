package com.alexis.shop.ui.menu.sizefilter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.databinding.SizeFilterItemLayoutBinding
import com.alexis.shop.domain.model.sizefilter.SizeFilterModel
import com.alexis.shop.utils.common.withDelay

class SizeFilterAdapter: RecyclerView.Adapter<SizeFilterAdapter.SizeViewHolder>() {

    private var sizeFilterData = ArrayList<List<SizeFilterModel>>()
    private val viewPool = RecyclerView.RecycledViewPool()
    private var selectedPosition = -1

    fun setData(data: ArrayList<List<SizeFilterModel>>) {
        sizeFilterData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        val binding = SizeFilterItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SizeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        holder.bind(sizeFilterData[position])
    }

    override fun getItemCount(): Int {
        return sizeFilterData.size
    }

    inner class SizeViewHolder(var binding: SizeFilterItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataList: List<SizeFilterModel>) {
            val itemAdapter = SizeFilterItemAdapter()
            itemAdapter.setData(dataList as ArrayList<SizeFilterModel>)
            val layoutManager = GridLayoutManager(binding.recylerViewChildItem.context, 4)
            layoutManager.initialPrefetchItemCount = dataList.size

            with(binding) {
                textTitle.text = dataList[0].selection
                recylerViewChildItem.layoutManager = layoutManager
                recylerViewChildItem.adapter = itemAdapter
                recylerViewChildItem.setRecycledViewPool(viewPool)
            }
        }
    }
}