package com.alexis.shop.ui.account.voucher

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.voucher.VoucherItemModel
import com.alexis.shop.utils.OnClickItem
import com.alexis.shop.utils.animation.Animations
import com.alexis.shop.utils.common.withDelay

class SimpleVoucherAdapter(private val context : Context, private val listener : OnClickItem)
    : RecyclerView.Adapter<SimpleVoucherAdapter.SimpleVoucherHolder>(){

    private var itemList = ArrayList<VoucherItemModel>()
    private var selectedPosition = -1

    fun setData(data : ArrayList<VoucherItemModel>) {
        itemList.clear()
        itemList = data
        notifyDataSetChanged()
    }
    inner class SimpleVoucherHolder(inflater: LayoutInflater, parent: ViewGroup) :
             RecyclerView.ViewHolder(inflater.inflate(R.layout.item_voucher_list, parent, false)) {

         var tanggal : TextView = itemView.findViewById(R.id.date)
         var name : TextView = itemView.findViewById(R.id.v_name)
         var expired : TextView = itemView.findViewById(R.id.v_exp)
         var potongan : TextView = itemView.findViewById(R.id.count)
         var select : ImageView = itemView.findViewById(R.id.img_btn)
         var border : ConstraintLayout = itemView.findViewById(R.id.round_border)
         fun bindItem(item : VoucherItemModel, position: Int) {
             tanggal.text = item.expiredDate
             name.text = item.name
             expired.text = item.expiredDate
             potongan.text = item.amount.toString()
             if (item.isSelected) {
                 border.background =
                     ContextCompat.getDrawable(
                         itemView.context,
                         R.drawable.rounder_white_transparent_withborder
                     )
             }
             itemView.setOnClickListener {
                 listener.onClick(item)
                 selectItem(position)
             }
         }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleVoucherHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SimpleVoucherHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: SimpleVoucherHolder, position: Int) {
        val item : VoucherItemModel = itemList[position]
        holder.bindItem(item, position)
        Animations.runAnimation(context, Animations.ANIMATION_IN, position, holder.itemView)
    }

    override fun getItemCount(): Int  = itemList.size

    private fun selectItem(position: Int) {
        if(position != selectedPosition) {
            if(selectedPosition > -1) {
                itemList[selectedPosition].isSelected = false
                notifyItemChanged(selectedPosition)
            }

            selectedPosition = position
            itemList[position].isSelected = true

            withDelay {
                notifyItemChanged(position)
            }
        }
    }
}