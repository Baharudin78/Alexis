package com.alexis.shop.ui.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.utils.popUpOnTop

class ReferAndEarnAdapter (private val lifecycleOwner: LifecycleOwner,
                           private val items : ArrayList<String>)
    : RecyclerView.Adapter<ReferAndEarnAdapter.ReferAndEarnViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReferAndEarnViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ReferAndEarnViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ReferAndEarnViewHolder, position: Int) {
        val item : String = items[position]

        holder.bind(lifecycleOwner, item)
    }

    override fun getItemCount(): Int = items.size

    class ReferAndEarnViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_refer_and_earn, parent, false)) {
        var title: TextView
        var step1: ImageView
        var step2: ImageView
        var step3: ImageView
        var panah1: ImageView
        var panah2: ImageView

        init {
            title = itemView.findViewById(R.id.name_refer)
            step1 = itemView.findViewById(R.id.btn_1)
            step2 = itemView.findViewById(R.id.btn_2)
            step3 = itemView.findViewById(R.id.btn_3)
            panah1 = itemView.findViewById(R.id.arrow1)
            panah2 = itemView.findViewById(R.id.arrow2)
        }

        fun bind(lifecycleOwner: LifecycleOwner, item: String) {
            title.text = item
            step1.setColorFilter(ContextCompat.getColor(itemView.context, R.color.white),
                    android.graphics.PorterDuff.Mode.SRC_IN)

            if(item.startsWith("D")){
                panah1.setColorFilter(ContextCompat.getColor(itemView.context, R.color.white),
                        android.graphics.PorterDuff.Mode.SRC_IN)
                step2.setColorFilter(ContextCompat.getColor(itemView.context, R.color.white),
                        android.graphics.PorterDuff.Mode.SRC_IN)
                panah2.setColorFilter(ContextCompat.getColor(itemView.context, R.color.white),
                        android.graphics.PorterDuff.Mode.SRC_IN)
                step3.setColorFilter(ContextCompat.getColor(itemView.context, R.color.white),
                        android.graphics.PorterDuff.Mode.SRC_IN)
            }else if(item.startsWith("M")){
                panah1.setColorFilter(ContextCompat.getColor(itemView.context, R.color.white),
                        android.graphics.PorterDuff.Mode.SRC_IN)
                step2.setColorFilter(ContextCompat.getColor(itemView.context, R.color.white),
                        android.graphics.PorterDuff.Mode.SRC_IN)
                panah2.setColorFilter(ContextCompat.getColor(itemView.context, R.color.white1),
                        android.graphics.PorterDuff.Mode.SRC_IN)
                step3.setColorFilter(ContextCompat.getColor(itemView.context, R.color.white1),
                        android.graphics.PorterDuff.Mode.SRC_IN)
            }else{
                panah1.setColorFilter(ContextCompat.getColor(itemView.context, R.color.white1),
                        android.graphics.PorterDuff.Mode.SRC_IN)
                step2.setColorFilter(ContextCompat.getColor(itemView.context, R.color.white1),
                        android.graphics.PorterDuff.Mode.SRC_IN)
                panah2.setColorFilter(ContextCompat.getColor(itemView.context, R.color.white1),
                        android.graphics.PorterDuff.Mode.SRC_IN)
                step3.setColorFilter(ContextCompat.getColor(itemView.context, R.color.white1),
                        android.graphics.PorterDuff.Mode.SRC_IN)
            }

            step1.setOnClickListener {
                (itemView.context).popUpOnTop(lifecycleOwner, "clicked").show(it)
            }
            step2.setOnClickListener {
                (itemView.context).popUpOnTop(lifecycleOwner, "registered").show(it)
            }
            step3.setOnClickListener {
                (itemView.context as AppCompatActivity).popUpOnTop("bought").show(it)
            }
        }
    }

}