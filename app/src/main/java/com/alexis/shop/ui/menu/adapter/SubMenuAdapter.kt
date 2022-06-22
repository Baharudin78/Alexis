package com.alexis.shop.ui.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R

class SubMenuAdapter(
    items : ArrayList<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<SubMenuAdapter.SubmenuViewHolder>() {

    private var contactList: ArrayList<String> = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubmenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SubmenuViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: SubmenuViewHolder, position: Int) {
        val item : String = contactList[position]

        holder.bind(item)

        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int = contactList.size
    
    class SubmenuViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.item_menu_submenu, parent, false)) {
        var title: TextView = itemView.findViewById(R.id.text_menu)

        fun bind(item: String) {
            title.apply {
                text = item
            }
        }
    }

}