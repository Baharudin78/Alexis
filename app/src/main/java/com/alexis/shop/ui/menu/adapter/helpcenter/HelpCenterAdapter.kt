package com.alexis.shop.ui.menu.adapter.helpcenter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.helpcenter.HelpCenterItemModel
import com.alexis.shop.utils.OnClickItem
import com.alexis.shop.utils.animation.Animations

class HelpCenterAdapter(
    private val context : Context,
    private val listener : OnClickItem
) : RecyclerView.Adapter<HelpCenterAdapter.HelpCenterHolder>(){

    private var helpList = ArrayList<HelpCenterItemModel>()

    fun setDataHelp(data : ArrayList<HelpCenterItemModel>) {
        helpList.clear()
        helpList = data
        notifyDataSetChanged()
    }

    class HelpCenterHolder(inflater : LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.item_text_only, parent, false)) {
                var title : TextView = itemView.findViewById(R.id.text_title)
                fun bindItem(item : HelpCenterItemModel) {
                    title.text = item.name
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpCenterHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HelpCenterHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: HelpCenterHolder, position: Int) {
        val item : HelpCenterItemModel = helpList[position]
        holder.bindItem(item)
        Animations.runAnimation(context, Animations.ANIMATION_IN, position, holder.itemView)
        holder.itemView.setOnClickListener {
            listener.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return helpList.size
    }
}