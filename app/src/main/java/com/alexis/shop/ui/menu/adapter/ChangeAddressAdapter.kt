package com.alexis.shop.ui.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.utils.common.withDelay
import com.alexis.shop.utils.gone
import com.alexis.shop.utils.OnClickItem
import com.alexis.shop.utils.visible

class ChangeAddressAdapter(
    private val items: ArrayList<String>,
    private val listener: OnClickItem,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<ChangeAddressAdapter.ChangeAddressViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChangeAddressViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ChangeAddressViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ChangeAddressViewHolder, position: Int) {
        val item: String = items[position]
        holder.bind(item, position == 0)
    }

    override fun getItemCount(): Int = items.size

    private fun resetTransition(motionLayout: MotionLayout?) {
        motionLayout?.let {
            it.progress = 0f
            it.setTransition(R.id.start, R.id.first_bounce)
        }
    }

    inner class ChangeAddressViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_change_address, parent, false)) {
        private var layout_base: ConstraintLayout = itemView.findViewById(R.id.detail_mode)
        private var text_base: TextView = itemView.findViewById(R.id.t_default)

        var title: TextView = itemView.findViewById(R.id.txt_1)
        var name: TextView = itemView.findViewById(R.id.txt_2)
        var address: TextView = itemView.findViewById(R.id.txt_3)
        var telp: TextView = itemView.findViewById(R.id.txt_4)
        var btn_delete: ImageView = itemView.findViewById(R.id.btn_delete_address)
        var motion: MotionLayout = itemView.findViewById(R.id.parent)

        fun bind(item: String, pos1: Boolean) {
            title.text = item
            name.text = "Alicia Wandow"
            address.text = "Taman Ratu Indah Blok E1 no 18\n" +
                    "Jl Jeruk Joglo Barat No 28\n" +
                    "Kembangan, Jakarta Barat\n" +
                    "DKI Jakarta 11730"
            telp.text = "Tel: +62 811 1482 3963"

            when (pos1) {
                true -> {
                    layout_base.background =
                        ContextCompat.getDrawable(
                            itemView.context,
                            R.drawable.rounder_white_transparent_withborder
                        )
                    text_base.visible()
                }
                else -> {
                    layout_base.background =
                        ContextCompat.getDrawable(
                            itemView.context,
                            R.drawable.rounder_white_transparent
                        )
                    text_base.gone()
                }
            }

            btn_delete.setOnClickListener {
                motion.transitionToEnd()
                withDelay(1000) {
                    onDeleteClick(adapterPosition)
                    withDelay(300) { resetTransition(motion) }
                }
            }
            itemView.setOnClickListener { listener.onClick(item) }
        }
    }
}