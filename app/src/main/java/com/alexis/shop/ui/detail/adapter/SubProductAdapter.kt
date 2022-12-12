package com.alexis.shop.ui.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.databinding.ItemsSubProductBinding
import com.alexis.shop.domain.model.product.modelbaru.ProductBaruModel
import com.alexis.shop.domain.model.wishlist.ProductItemModel
import com.alexis.shop.utils.OnClickItem
import com.bumptech.glide.Glide

class SubProductAdapter(
    private val context : Context,
    private val listener : OnClickItem
) : RecyclerView.Adapter<SubProductAdapter.SubProductHolder>(){

    private var itemProduct = ArrayList<ProductBaruModel>()

    fun setData(data : ArrayList<ProductBaruModel>){
        itemProduct.clear()
        itemProduct = data
        notifyDataSetChanged()
    }

    inner class SubProductHolder(val itemBinding : ItemsSubProductBinding) : RecyclerView.ViewHolder(itemBinding.root){
        val title = itemBinding.title
        val photo = itemBinding.gmbBara
        val price = itemBinding.harga

        fun bindItem(item : ProductBaruModel){
            title.text = item.name
            Glide.with(context)
                .load(item.product_image)
                .error(R.drawable.cs)
                .into(photo)
            price.text = item.price.toString()

            itemBinding.root.setOnClickListener {
                listener.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubProductHolder {
        val inflater = ItemsSubProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubProductHolder(inflater)
    }

    override fun onBindViewHolder(holder: SubProductHolder, position: Int) {
        val item : ProductBaruModel = itemProduct[position]
        holder.bindItem(item)
    }

    override fun getItemCount(): Int {
        return itemProduct.size
    }
}