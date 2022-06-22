package com.alexis.shop.ui.menu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.utils.OnClickItem

class PointsAdapter (private val context: Context,
                     private val items : ArrayList<String>,
                     private val listener: OnClickItem
)
    : RecyclerView.Adapter<PointsAdapter.PointsViewHolder>() {

    private var contactList: ArrayList<String> = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PointsViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: PointsViewHolder, position: Int) {
        val item : String = contactList[position]

        holder.bind(context, item)

        holder.itemView.setOnClickListener { listener.onClick(item) }
    }

    override fun getItemCount(): Int = contactList.size

    class PointsViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_points_list, parent, false)) {
        var text1: TextView
        var text2: TextView
        var text3: TextView
        var text4: TextView

        init {
            text1 = itemView.findViewById(R.id.id1)
            text2 = itemView.findViewById(R.id.id2)
            text3 = itemView.findViewById(R.id.id3)
            text4 = itemView.findViewById(R.id.id4)
        }

        fun bind(context: Context, item: String) {
            text1.text = item
        }
    }

}