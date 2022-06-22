package com.alexis.shop.ui.menu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.utils.gone
import com.alexis.shop.utils.visible

class OrderDetailAdapter (private val context: Context,
                         private val items : ArrayList<String>
): RecyclerView.Adapter<OrderDetailAdapter.OrderDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return OrderDetailViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: OrderDetailViewHolder, position: Int) {
        val item : String = items[position]
        val tesssss =
                when (position) {
                    (items.size - 1) -> true
                    else -> false
                }

        holder.bind(context, item, tesssss)
    }

    override fun getItemCount(): Int = items.size

    class OrderDetailViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_order_detail, parent, false)) {
        var order_name: TextView
        var order_code: TextView
        var order_size: TextView
        var order_price: TextView

        var imageView: ImageView
        var blok_returned: View
        var text_returned: TextView

        init {
            order_name = itemView.findViewById(R.id.order_name)
            order_code = itemView.findViewById(R.id.order_code)
            order_size = itemView.findViewById(R.id.order_size)
            order_price = itemView.findViewById(R.id.order_price)

            imageView = itemView.findViewById(R.id.order_image)
            blok_returned = itemView.findViewById(R.id.blok_returned)
            text_returned = itemView.findViewById(R.id.order_returned)
        }

        fun bind(context: Context, item: String, tessss: Boolean) {
            order_name.text = item

            when(tessss){
                true -> {
                    blok_returned.visible()
                    text_returned.visible()
                }
                else -> {
                    blok_returned.gone()
                    text_returned.gone()
                }
            }
        }
    }

}