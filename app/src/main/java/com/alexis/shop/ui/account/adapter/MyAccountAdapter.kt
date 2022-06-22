package com.alexis.shop.ui.account.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.menu.MenuModel
import com.alexis.shop.utils.OnClickItem

class MyAccountAdapter (private val context: Context,
						private val items : ArrayList<MenuModel>,
						private val listener: OnClickItem
)
    : RecyclerView.Adapter<MyAccountViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAccountViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyAccountViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MyAccountViewHolder, position: Int) {
        val item : MenuModel = items[position]

        holder.bind(context, item)

        holder.itemView.setOnClickListener {
            listener.onClick(item)
        }
    }

    override fun getItemCount(): Int = items.size
}

class MyAccountViewHolder (inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_menu_myaccount, parent, false)) {
    var title: TextView = itemView.findViewById(R.id.text_menu)

    fun bind(context: Context, item: MenuModel) {
        title.setCompoundDrawablesWithIntrinsicBounds(item.icon, 0, 0, 0)
        title.text = item.title
    }
}
