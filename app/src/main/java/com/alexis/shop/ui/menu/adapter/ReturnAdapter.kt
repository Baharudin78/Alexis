package com.alexis.shop.ui.menu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.order.ReturnModel
import com.alexis.shop.utils.gone
import com.alexis.shop.utils.OnReturnClickItem
import com.alexis.shop.utils.visible

class ReturnAdapter (private val context: Context,
					 private val items : ArrayList<ReturnModel>,
					 private val listener: OnReturnClickItem)
    : RecyclerView.Adapter<ReturnAdapter.ReturnViewHolder>() {

    private var contactList: ArrayList<ReturnModel> = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReturnViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ReturnViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ReturnViewHolder, position: Int) {
        val item : ReturnModel = contactList[position]

        holder.bind(context, item)

        holder.itemView.setOnClickListener { listener.onClick(item) }

        holder.itemView.findViewById<TextView>(R.id.res1)
                .setOnClickListener { listener.onLongClick(item, (it as TextView).text.toString())}
        holder.itemView.findViewById<TextView>(R.id.res2)
                .setOnClickListener { listener.onLongClick(item, (it as TextView).text.toString())}
        holder.itemView.findViewById<TextView>(R.id.res3)
                .setOnClickListener { listener.onLongClick(item, (it as TextView).text.toString())}
        holder.itemView.findViewById<TextView>(R.id.res4)
                .setOnClickListener { listener.onLongClick(item, (it as TextView).text.toString())}
        holder.itemView.findViewById<TextView>(R.id.res5)
                .setOnClickListener { listener.onLongClick(item, (it as TextView).text.toString())}
        holder.itemView.findViewById<TextView>(R.id.res6)
                .setOnClickListener { listener.onLongClick(item, (it as TextView).text.toString())}
    }

    override fun getItemCount(): Int = contactList.size

    class ReturnViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.item_return_list, parent, false)) {
        var base: ConstraintLayout
        var layoutBawah: ConstraintLayout
        var image: ImageView
        var title: TextView
        var code: TextView
        var size: TextView
        var price: TextView
        var reason: TextView

        init {
            base = itemView.findViewById(R.id.detail_mode)
            layoutBawah = itemView.findViewById(R.id.ly_bot)
            image = itemView.findViewById(R.id.item_image)
            title = itemView.findViewById(R.id.item_title)
            code = itemView.findViewById(R.id.item_code)
            size = itemView.findViewById(R.id.item_size)
            price = itemView.findViewById(R.id.item_price)
            reason = itemView.findViewById(R.id.status_return)
        }

        fun bind(context: Context, item: ReturnModel) {
            title.text = item.getItem()

            when (item.getScaled()){
                true -> {
                    layoutBawah.visible()
                    base.background =
                            ContextCompat.getDrawable(itemView.context,
                                    R.drawable.rounder_white_transparent_withborder)
                }
                else -> {
                    layoutBawah.gone()

                    when(item.getSelected()){
                        true -> {
                            base.background =
                                    ContextCompat.getDrawable(itemView.context,
                                            R.drawable.rounder_white_transparent_withborder)
                            reason.visible()
                            reason.text = item.getReason()
                        }
                        else -> {
                            base.background =
                                    ContextCompat.getDrawable(itemView.context,
                                            R.drawable.rounder_white_transparent)
                            reason.gone()
                        }
                    }
                }
            }
        }
    }
}