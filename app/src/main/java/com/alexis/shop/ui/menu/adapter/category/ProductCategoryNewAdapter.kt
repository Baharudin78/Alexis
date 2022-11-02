package com.alexis.shop.ui.menu.adapter.category

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.product.category.ProductCategoryNewModel
import com.alexis.shop.domain.model.product.category.SubCategoryModel
import com.alexis.shop.utils.OnClickItem
import com.alexis.shop.utils.animation.Animations

class ProductCategoryNewAdapter(
    private val context: Context,
    private val listener: OnClickItem
) : RecyclerView.Adapter<ProductCategoryNewAdapter.CategoryViewHolder>() {

    private var categoryList = ArrayList<ProductCategoryNewModel>()

    fun setDataProduct(data : ArrayList<ProductCategoryNewModel>) {
        categoryList.clear()
        categoryList = data
    }

    class CategoryViewHolder(inflater : LayoutInflater, parent: ViewGroup
    ) : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_product_category_new, parent, false)) {
        var listSubProduct : List<SubCategoryModel> = ArrayList()
        var linearLayout : LinearLayout = itemView.findViewById(R.id.linear_layout)
        var expandableLayout : RelativeLayout = itemView.findViewById(R.id.expandable_layout)
        var mTextView : TextView = itemView.findViewById(R.id.itemTv)
        var nestedRecyclerView : RecyclerView = itemView.findViewById(R.id.child_rv)
        val subAdapter = SubCategoryNewAdapter(itemView.context)

        fun bindData(item :ProductCategoryNewModel ) {
            Log.d("TAGSS", item.category)
            mTextView.text = item.category
            val isExpandable = item.isExpandable
            expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE
            nestedRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            nestedRecyclerView.adapter = subAdapter
            linearLayout.setOnClickListener {
                item.isExpandable = !item.isExpandable
                listSubProduct = item.sub_category
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoryViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val produk = categoryList[position]
        holder.bindData(produk)
        Animations.runAnimation(context, Animations.ANIMATION_IN, position, holder.itemView)
        holder.linearLayout.setOnClickListener {
            listener.onClick(produk.category)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }


}