package com.alexis.shop.ui.account.voucher

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.voucher.AllVoucherModel
import com.alexis.shop.domain.model.voucher.VoucherItemModel
import com.alexis.shop.utils.OnClickItem
import com.alexis.shop.utils.animation.Animations

class SimpleVoucherAdapter(private val context : Context, private val listener : OnClickItem)
    : RecyclerView.Adapter<SimpleVoucherAdapter.SimpleVoucherHolder>(){

    private var itemList = ArrayList<VoucherItemModel>()

    fun setData(data : ArrayList<VoucherItemModel>) {
        itemList.clear()
        itemList = data
        notifyDataSetChanged()
    }
     class SimpleVoucherHolder(inflater: LayoutInflater, parent: ViewGroup) :
             RecyclerView.ViewHolder(inflater.inflate(R.layout.item_voucher_list, parent, false)) {

         var tanggal : TextView = itemView.findViewById(R.id.date)
         var name : TextView = itemView.findViewById(R.id.v_name)
         var expired : TextView = itemView.findViewById(R.id.v_exp)
         var potongan : TextView = itemView.findViewById(R.id.count)

         fun bindItem(item : VoucherItemModel) {
             tanggal.text = item.expiredDate
             name.text = item.voucherType?.nameType
             expired.text = item.expiredDate
             potongan.text = item.discount.toString()
         }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleVoucherHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SimpleVoucherHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: SimpleVoucherHolder, position: Int) {
        val item : VoucherItemModel = itemList[position]
        holder.bindItem(item)
        Animations.runAnimation(context, Animations.ANIMATION_IN, position, holder.itemView)
    }

    override fun getItemCount(): Int  = itemList.size
}