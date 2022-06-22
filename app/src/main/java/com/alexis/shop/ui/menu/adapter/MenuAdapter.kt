package com.alexis.shop.ui.menu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.domain.model.menu.MenuModel
import com.alexis.shop.utils.animation.Animations
import com.alexis.shop.utils.OnClickItem

class MenuAdapter (private val context: Context,
				   private val items : ArrayList<MenuModel>,
				   private val listener: OnClickItem
)
    : RecyclerView.Adapter<MenuViewHolder>() {

    private var contactList: ArrayList<MenuModel> = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MenuViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item : MenuModel = contactList[position]

        holder.bind(context, item)

        Animations.runAnimation(
            context,
            Animations.ANIMATION_IN,
            position,
            holder.itemView
        )

        holder.itemView.setOnClickListener {
            listener.onClick(item)
        }
    }

    override fun getItemCount(): Int = contactList.size

}