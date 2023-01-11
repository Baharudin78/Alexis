package com.alexis.shop.ui.shopping_bag.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.databinding.ItemChangeAddressBinding
import com.alexis.shop.domain.model.address.AddressItemModel
import com.alexis.shop.domain.model.checkout.CheckoutAddressModelView
import com.alexis.shop.utils.*
import com.alexis.shop.utils.common.withDelay

class SelectAddressAdapter(
    private val context : Context,
    private val listener: OnAddressClick
) : RecyclerView.Adapter<SelectAddressAdapter.SelectAddressViewHolder>() {

    private val items = ArrayList<AddressItemModel>()
    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectAddressViewHolder {
        val inflater = ItemChangeAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SelectAddressViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: SelectAddressViewHolder, position: Int) {
        val item = items[position]
        val motion = holder.binding.parent
        holder.bindItem(item, position)
        holder.binding.btnDeleteAddress.setOnClickListener {
            motion.transitionToEnd()
            listener.delete(item)
            resetAnimation(motion)
        }
        holder.binding.btnEdit.setOnClickListener {
            listener.updateItem(item)
        }
        holder.binding.btnDropship.setOnClickListener {
            listener.onDropship(item)
        }
    }

    override fun getItemCount(): Int = items.size

    fun setData(data: ArrayList<AddressItemModel>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    inner class SelectAddressViewHolder(val binding : ItemChangeAddressBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindItem(item : AddressItemModel, position: Int){
            with(binding){
                txt1.text = item.address
                txt2.text = item.recipientName
                val fullAddress = item.address + item.addressTwo
                txt3.text = fullAddress
                txt4.text = item.recipientPhoneNumber
                if (item.isSelectedItem) {
                    detailMode.background =
                        ContextCompat.getDrawable(
                            itemView.context,
                            R.drawable.rounder_white_transparent_withborder
                        )
                }else{
                    detailMode.background =
                        ContextCompat.getDrawable(
                            itemView.context,
                            R.drawable.rounder_white_transparent
                        )
                }
                detailMode.setOnClickListener {
                    listener.onClick(item)
                    selectItem(position)
                }
            }
        }
    }

    private fun resetAnimation(motionLayout : MotionLayout?){
        motionLayout?.let {
            it.progress = 0f
            it.setTransition(R.id.start, R.id.first_bounce)
        }
    }

    private fun selectItem(position: Int) {
//        items.removeAt(position)
//        items.add(index = 0, items[position])
//        notifyDataSetChanged()
//
//
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
}
