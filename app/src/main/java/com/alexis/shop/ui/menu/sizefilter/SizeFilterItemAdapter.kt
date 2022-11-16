package com.alexis.shop.ui.menu.sizefilter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.databinding.SizeFilterChildItemLayoutBinding
import com.alexis.shop.domain.model.sizefilter.SizeFilterModel
import com.alexis.shop.utils.OnClickItem
import com.alexis.shop.utils.common.withDelay

class SizeFilterItemAdapter(
    private var listener : OnClickItem
) : RecyclerView.Adapter<SizeFilterItemAdapter.SizeItemViewHolder>() {
    private var sizeFilterItemData = ArrayList<SizeFilterModel>()
    private var selectedPosition = -1

    fun setData(data: ArrayList<SizeFilterModel>) {
        sizeFilterItemData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SizeFilterItemAdapter.SizeItemViewHolder {
        val binding = SizeFilterChildItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SizeItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SizeFilterItemAdapter.SizeItemViewHolder, position: Int) {
        holder.bind(sizeFilterItemData[position], position)
    }

    override fun getItemCount(): Int {
        return sizeFilterItemData.size
    }

    inner class SizeItemViewHolder(var binding: SizeFilterChildItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SizeFilterModel, position: Int) {
            with(binding) {
                textViewChild.text = item.name
                if (item.isSelected) {
                    Log.d("SADHSAIDAS", "${item.id}")
                    textViewChild.setTextColor(ContextCompat.getColor(root.context, R.color.transparent_expanmenu))
                    textViewChild.background = ContextCompat.getDrawable(root.context,R.drawable.rounder_white_background_10dp)
                }
                itemView.setOnClickListener {
                    listener.onClick(item)
                    selectItem(position)
                }
//                root.setOnClickListener {
//                    listener.onClick(item)
//                    selectItem(position)
//                }
            }
        }
    }

    private fun selectItem(position: Int) {
        if(position != selectedPosition) {
            if(selectedPosition > -1) {
                sizeFilterItemData[selectedPosition].isSelected = false
                notifyItemChanged(selectedPosition)
            }

            selectedPosition = position
            sizeFilterItemData[position].isSelected = true
            withDelay {
                notifyItemChanged(position)
            }
        }
    }
}