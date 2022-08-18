package com.alexis.shop.ui.menu.adapter.categoryproduct

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.databinding.ItemMenuProductBinding

class CategoryProductAdapter() : RecyclerView.Adapter<CategoryProductAdapter.CategoryProductHolder>() {

    inner class CategoryProductHolder(val binding : ItemMenuProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryProductHolder {
        val inflated = ItemMenuProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryProductHolder(inflated)
    }

    override fun onBindViewHolder(holder: CategoryProductHolder, position: Int) {
        TODO("Not yet implemented")
    }
}