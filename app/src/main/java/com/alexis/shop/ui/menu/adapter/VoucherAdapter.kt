package com.alexis.shop.ui.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.utils.*

val VOUCHER_FRAGMENT = 1
val VOUCHER_SELECTOR_FRAGMENT = 2
val VOUCHER_PROMO_SELECTOR_FRAGMENT = 3

class VoucherAdapter (private val id_source: Int,
                      private val items : ArrayList<String>,
                      private val listener: OnClickItem
) : RecyclerView.Adapter<VoucherAdapter.VoucherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoucherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return VoucherViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: VoucherViewHolder, position: Int) {
        val item : String = items[position]

        holder.bind(id_source, item)

        holder.itemView.setOnClickListener { listener.onClick(item) }
        holder.vou_btn_add.setOnClickListener {
            // do things
            holder.vou_btn_add.setImageResource(R.drawable.ic_check)
        }
    }

    override fun getItemCount(): Int = items.size

    class VoucherViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_voucher_list, parent, false)) {
        var vou_image: ImageView = itemView.findViewById(R.id.image)
        var vou_date: TextView = itemView.findViewById(R.id.date)
        var vou_name: TextView = itemView.findViewById(R.id.v_name)
        var vou_exp: TextView = itemView.findViewById(R.id.v_exp)
        var vou_count: TextView = itemView.findViewById(R.id.count)
        var vou_info1: TextView = itemView.findViewById(R.id.info1)
        var vou_info2: TextView = itemView.findViewById(R.id.info2)
        var vou_btn_add: ImageView = itemView.findViewById(R.id.img_btn)
        var vou_info_add: ConstraintLayout = itemView.findViewById(R.id.info_add)


        fun bind(id_source: Int, item: String) {
            vou_name.text = item
            when (id_source){
                VOUCHER_FRAGMENT -> {
                    vou_info1.visible()
                    vou_info2.visible()
                    vou_info_add.gone()
                }
                else -> {
                    vou_info1.invisible()
                    vou_info2.invisible()
                    vou_info_add.visible()
                }
            }

        }
    }
}