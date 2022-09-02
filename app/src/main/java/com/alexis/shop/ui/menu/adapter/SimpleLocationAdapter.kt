package com.alexis.shop.ui.menu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.store_location.AllStoreItemModel
import com.alexis.shop.utils.animation.Animations
import com.alexis.shop.utils.OnClickItem

class SimpleLocationAdapter (private val context: Context, private val listener: OnClickItem)
    : RecyclerView.Adapter<SimpleLocationAdapter.SimpleLocationViewHolder>() {

    private var contactList = ArrayList<AllStoreItemModel>()

    fun setData(data: ArrayList<AllStoreItemModel>) {
        contactList.clear()
        contactList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleLocationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SimpleLocationViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: SimpleLocationViewHolder, position: Int) {
        val item : AllStoreItemModel =contactList[position]
        holder.bind(item)
        Animations.runAnimation(context, Animations.ANIMATION_IN, position, holder.itemView)
        holder.itemView.setOnClickListener { listener.onClick(item.province) }
    }

    override fun getItemCount(): Int = contactList.size

    class SimpleLocationViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.item_text_only, parent, false)) {
        var title: TextView = itemView.findViewById(R.id.text_title)

        fun bind(item: AllStoreItemModel) {
            title.text = item.province
        }
    }
}