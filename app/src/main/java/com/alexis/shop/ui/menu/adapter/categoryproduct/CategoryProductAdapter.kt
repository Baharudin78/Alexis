package com.alexis.shop.ui.menu.adapter.categoryproduct

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.databinding.ItemMenuProductBinding
import com.alexis.shop.domain.model.product.category.ProductCategoryModel
import com.alexis.shop.utils.OnClickItem

class CategoryProductAdapter(
    private val context : Context,
    private val listener : OnClickItem
) : RecyclerView.Adapter<CategoryProductAdapter.CategoryProductHolder>() {

    private var categoryList = ArrayList<ProductCategoryModel>()

    fun setDataCategory(data : ArrayList<ProductCategoryModel>) {
        categoryList.clear()
        categoryList = data
    }
    class CategoryProductHolder(val binding : ItemMenuProductBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryProductHolder {
        val inflated = ItemMenuProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryProductHolder(inflated)
    }

    override fun onBindViewHolder(holder: CategoryProductHolder, position: Int) {
        val item : ProductCategoryModel = categoryList[position]
        holder
    }
}