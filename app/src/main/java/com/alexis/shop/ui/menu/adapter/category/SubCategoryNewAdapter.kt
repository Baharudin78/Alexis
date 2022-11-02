package com.alexis.shop.ui.menu.adapter.category

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.data.remote.response.product.categoritwo.SubCategoryItem
import com.alexis.shop.domain.model.product.category.SubCategoryModel
import com.alexis.shop.utils.OnClickItem

class SubCategoryNewAdapter(
    private val context: Context,
) : RecyclerView.Adapter<SubCategoryNewAdapter.SubCategoryHolder>(){
    private val listSubCategory = ArrayList<SubCategoryModel>()

    class SubCategoryHolder(inflater : LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_nested, parent, false)){
        val subProduct : TextView = itemView.findViewById(R.id.nestedItemTv)
        fun bind(item : SubCategoryModel) {
            subProduct.text = item.merchandise_name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SubCategoryHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: SubCategoryHolder, position: Int) {
        val subProduk = listSubCategory[position]
        holder.bind(subProduk)
    }

    override fun getItemCount(): Int {
        return listSubCategory.size
    }
}