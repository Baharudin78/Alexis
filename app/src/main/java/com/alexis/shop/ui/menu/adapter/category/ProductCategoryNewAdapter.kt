package com.alexis.shop.ui.menu.adapter.category

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.product.category.ProductCategoryNewItem
import com.alexis.shop.domain.model.product.category.ProductCategoryNewModel
import com.alexis.shop.domain.model.product.category.SubCategoryModel
import com.alexis.shop.utils.OnClickItem
import com.alexis.shop.utils.animation.Animations

class ProductCategoryNewAdapter(
    private val context: Context,
    private val listener: OnClickItem
) : RecyclerView.Adapter<ProductCategoryNewAdapter.CategoryViewHolder>() {

    private var categoryList = ArrayList<ProductCategoryNewItem>()

    fun setDataProduct(data : ArrayList<ProductCategoryNewItem>) {
        categoryList.clear()
        categoryList = data
        notifyDataSetChanged()
        Log.d("ADAPTERE", "$data")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoryViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val produk : ProductCategoryNewItem = categoryList[position]
        holder.bindData(produk)
        Animations.runAnimation(context, Animations.ANIMATION_IN, position, holder.itemView)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

   inner class CategoryViewHolder(inflater : LayoutInflater, parent: ViewGroup
    ) : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_product_category_new, parent, false)) {
        var listSubProduct : List<SubCategoryModel> = ArrayList()
        var expandableLayout : RelativeLayout = itemView.findViewById(R.id.expandable_layout)
        var mTextView : TextView = itemView.findViewById(R.id.itemTv)
        var nestedRecyclerView : RecyclerView = itemView.findViewById(R.id.child_rv)
        var subAdapter = SubCategoryNewAdapter(itemView.context, listener)

        fun bindData(item :ProductCategoryNewItem ) {
            mTextView.text = item.category
            val isExpandable = item.isExpandable
            expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.VISIBLE
            nestedRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            nestedRecyclerView.adapter = subAdapter
            subAdapter.setDataSub(listSubProduct as ArrayList<SubCategoryModel>)
            mTextView.setOnClickListener {
                item.isExpandable = !item.isExpandable
                listSubProduct = item.sub_category
                subAdapter.setDataSub(listSubProduct as ArrayList<SubCategoryModel>)
            }
        }
    }
}