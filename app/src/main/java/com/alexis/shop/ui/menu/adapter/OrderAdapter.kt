package com.alexis.shop.ui.menu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.product.ImageModel
import com.alexis.shop.ui.detail.adapter.ImageOrderAdapter
import com.alexis.shop.utils.animation.Animations
import com.alexis.shop.utils.OnClickItem

class OrderAdapter (private val context: Context,
                    private val items : ArrayList<String>,
                    private val listener: OnClickItem
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    private var contactList: ArrayList<String> = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return OrderViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item: String = contactList[position]

        Animations.runAnimation(
            context,
            Animations.ANIMATION_IN,
            position,
            holder.itemView
        )

        holder.bind(context, item, listener)

        holder.itemView.setOnClickListener { listener.onClick(item) }
    }

    override fun getItemCount(): Int = contactList.size

    class OrderViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.item_order_list, parent, false)) {
        var date: TextView = itemView.findViewById(R.id.date_order)
        var status: TextView = itemView.findViewById(R.id.status_order)
        var price: TextView = itemView.findViewById(R.id.harga_order)
        var discount: TextView = itemView.findViewById(R.id.diskon_order)

        var recycle_img: RecyclerView = itemView.findViewById(R.id.image_order)

        fun bind(context: Context, item_it: String, listener: OnClickItem) {
            date.text = item_it

            val images = ArrayList<ImageModel>()
            images.add(ImageModel("https://cdn.tobi.com/product_images/sm/2/grey-ayda-cowl-neck-sweater-dress.jpg", false))
            images.add(ImageModel("https://cdn.tobi.com/product_images/sm/1/grey-ayda-cowl-neck-sweater-dress.jpg", false))
            images.add(ImageModel("https://cdn.tobi.com/product_images/sm/3/grey-ayda-cowl-neck-sweater-dress.jpg", false))
            images.add(ImageModel("https://cdn.tobi.com/product_images/sm/4/grey-ayda-cowl-neck-sweater-dress.jpg", false))

//            recycle_img.apply {
//                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//                adapter = ImageOrderAdapter(context, images) {
//                    listener.onClick(item_it)
//                }
//            }
        }
    }
}