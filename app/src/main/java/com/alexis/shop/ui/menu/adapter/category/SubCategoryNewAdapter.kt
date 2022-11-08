package com.alexis.shop.ui.menu.adapter.category

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.product.category.SubCategoryModel
import com.alexis.shop.utils.animation.Animations

class SubCategoryNewAdapter(
    private val context: Context,
) : RecyclerView.Adapter<SubCategoryNewAdapter.SubCategoryHolder>(){
    private var listSubCategory = ArrayList<SubCategoryModel>()

    fun setDataSub(data : ArrayList<SubCategoryModel>) {
        listSubCategory.clear()
        listSubCategory = data
        notifyDataSetChanged()
        Log.d("ADAPTEREss", "$data")
    }
    class SubCategoryHolder(inflater : LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_nested, parent, false)){
        val subProduct : TextView = itemView.findViewById(R.id.nestedItemTv)
        fun bind(item : SubCategoryModel) {
            subProduct.text = item.merchandise_name
            Log.d("SUBSDUSFxx", "${item.merchandise_name}")

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SubCategoryHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: SubCategoryHolder, position: Int) {
        val subProduk = listSubCategory[position]
        holder.bind(subProduk)
        Animations.runAnimation(context, Animations.ANIMATION_IN, position, holder.itemView)
    }

    override fun getItemCount(): Int {
        return listSubCategory.size
    }
}