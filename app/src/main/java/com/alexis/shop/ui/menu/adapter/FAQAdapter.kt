package com.alexis.shop.ui.menu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.faq.FAQModel

class FAQAdapter (private val context: Context,
                  private val items : ArrayList<FAQModel>,
                  private val onClick: (FAQModel) -> Unit
): RecyclerView.Adapter<FAQAdapter.FAQViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FAQViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FAQViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: FAQViewHolder, position: Int) {
        val item : FAQModel = items[position]
        holder.bind(context, item)

        holder.itemView.setOnClickListener {
            onClick(item)
            /**
             * trigger animation when visibility change,
             * but can't work when notifyDataSetChanged called
             * **/
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = items.size

    class FAQViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_frequent_question, parent, false)) {
        var text_atas: TextView = itemView.findViewById(R.id.text1)
        var layout_bawah: ConstraintLayout = itemView.findViewById(R.id.text_bottom)
        var text_bawah: TextView = itemView.findViewById(R.id.text2)

        fun bind(context: Context, item: FAQModel) {
            text_atas.text = item.getData()
            text_atas.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_icondown, 0)
            text_bawah.text = "Once your order has been completed, you will " +
                    "receive an email confirmation for it. If you do not " +
                    "receive one, contact our customer service " +
                    "department."

            layout_bawah.isVisible = item.getChoosed()
//            val slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up)
//            val slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down)
            when (item.getChoosed()) {
                false -> {
                    text_atas.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_icondown, 0)
                }
                else -> {
                    text_atas.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_iconup, 0)
                }
            }
        }
    }

}