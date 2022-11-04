package com.alexis.shop.data.source.dummy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R


class ItemAdapter(private val mList: List<DataModel>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder?>() {
    private var list: List<String> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product_category_new, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val model = mList[position]
        holder.mTextView.text = model.itemText
        val isExpandable = model.isExpandable
        holder.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE
        if (isExpandable) {
        } else {
        }
        val adapter = NestedAdapter(list)
        holder.nestedRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.nestedRecyclerView.setHasFixedSize(true)
        holder.nestedRecyclerView.adapter = adapter
//        holder.linearLayout.setOnClickListener {
//            model.isExpandable = !model.isExpandable
//            list = model.nestedList
//            notifyItemChanged(holder.adapterPosition)
//        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       //  val linearLayout: LinearLayout
         val expandableLayout: RelativeLayout
         val mTextView: TextView
         val nestedRecyclerView: RecyclerView

        init {
         //   linearLayout = itemView.findViewById(R.id.linear_layout)
            expandableLayout = itemView.findViewById(R.id.expandable_layout)
            mTextView = itemView.findViewById(R.id.itemTv)
            nestedRecyclerView = itemView.findViewById(R.id.child_rv)
        }
    }
}