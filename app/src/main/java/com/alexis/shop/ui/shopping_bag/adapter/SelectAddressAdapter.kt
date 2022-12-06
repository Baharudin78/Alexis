package com.alexis.shop.ui.shopping_bag.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.address.AddressItemModel
import com.alexis.shop.domain.model.checkout.CheckoutAddressModelView
import com.alexis.shop.utils.*
import com.alexis.shop.utils.common.withDelay

class SelectAddressAdapter(
    private val context : Context,
    private val listener: OnClickItem
) : RecyclerView.Adapter<SelectAddressAdapter.SelectAddressViewHolder>() {

    private val items = ArrayList<AddressItemModel>()
    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectAddressViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SelectAddressViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: SelectAddressViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, position)
       // holder.itemView.setOnClickListener { listener.onClick(item) }
    }

    override fun getItemCount(): Int = items.size

    fun setData(data: ArrayList<AddressItemModel>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

   inner class SelectAddressViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_change_address, parent, false)) {
        var title: TextView = itemView.findViewById(R.id.txt_1)
        var name: TextView = itemView.findViewById(R.id.txt_2)
        var address: TextView = itemView.findViewById(R.id.txt_3)
        var telp: TextView = itemView.findViewById(R.id.txt_4)
        var parent: MotionLayout = itemView.findViewById(R.id.parent)

        var mode_normal: ConstraintLayout = itemView.findViewById(R.id.detail_mode)
        var btn_delete: ImageView = itemView.findViewById(R.id.btn_delete_address)
        var btn_dropship: ImageView = itemView.findViewById(R.id.btn_dropship)
        var btn_edit: ImageView = itemView.findViewById(R.id.btn_edit)

        fun bind(item: AddressItemModel, position: Int) {
            title.text = item.address
            name.text = item.recipientName
            val fullAddress = item.address + item.addressTwo
            address.text = fullAddress
            telp.text = item.recipientPhoneNumber

            itemView.setOnClickListener {
                listener.onClick(item)
                selectItem(position)
            }
            if (item.isDefault!! == -1) {
                mode_normal.gone()
            } else {
                mode_normal.visible()
                title.text = item.address
                name.text = item.recipientName
                val fullAddress = item.address + item.addressTwo
                address.text = fullAddress
                telp.text = item.recipientPhoneNumber

                when (item.isDefault) {
                    1 -> mode_normal.background =
                        ContextCompat.getDrawable(
                            itemView.context,
                            R.drawable.rounder_white_transparent_withborder
                        )
                    else -> mode_normal.background =
                        ContextCompat.getDrawable(
                            itemView.context,
                            R.drawable.rounder_white_transparent
                        )
                }

                setButtonDelete()
                setButtonDropship(item.asDropship)
            }
        }

        private fun setButtonDelete() {
            btn_delete.invisible()
            btn_delete.setOnClickListener {
                parent.transitionToEnd()
            }
        }

       private fun selectItem(position: Int) {
           if (position != selectedPosition ) {
               items[selectedPosition].isSelectedItem = false
               notifyItemChanged(selectedPosition)
           }
           selectedPosition = position
           items[position].isSelectedItem = true
           withDelay {
               notifyItemChanged(position)
           }
       }

        private fun setButtonDropship(isShow: Int?) {
            if (isShow == 1) {
                btn_dropship.visible()
            } else {
                btn_dropship.invisible()
            }
            btn_dropship.isClickable
        }
    }
}