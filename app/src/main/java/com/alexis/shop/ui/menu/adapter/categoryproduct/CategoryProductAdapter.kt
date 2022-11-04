package com.alexis.shop.ui.menu.adapter.categoryproduct

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.product.category.ProductCategoryNewModel
import com.alexis.shop.utils.OnClickItem
import com.alexis.shop.utils.animation.Animations

class CategoryProductAdapter(
    private val context : Context,
    private val listener : OnClickItem
) : RecyclerView.Adapter<CategoryProductAdapter.CategoryProductHolder>() {

    private var categoryList = ArrayList<ProductCategoryNewModel>()

    fun setDataCategory(data : ArrayList<ProductCategoryNewModel>) {
        categoryList.clear()
        categoryList = data
    }
    class CategoryProductHolder(inflater : LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_product_category, parent, false)) {
            var title : TextView = itemView.findViewById(R.id.tv_product_category)
            fun bind(item : ProductCategoryNewModel ) {
             //   title.text = item.category
            }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoryProductHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CategoryProductHolder, position: Int) {
        val item : ProductCategoryNewModel = categoryList[position]
        holder.bind(item)
        Animations.runAnimation(context, Animations.ANIMATION_IN, position, holder.itemView)
        holder.itemView.setOnClickListener {
         //   listener.onClick(item.category)
        }
    }
}