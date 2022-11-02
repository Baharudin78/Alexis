package com.alexis.shop.data.source.dummy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R

class NestedAdapter(private val mList: List<String>) :
    RecyclerView.Adapter<NestedAdapter.NestedViewHolder?>() {
    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): NestedViewHolder {
        val view: View =
            LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nested, parent, false)
        return NestedViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull holder: NestedViewHolder, position: Int) {
        holder.mTv.setText(mList[position])

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class NestedViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
         val mTv: TextView

        init {
            mTv = itemView.findViewById(R.id.nestedItemTv)
        }
    }
}