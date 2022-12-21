package com.alexis.shop.ui.detail.adapter

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.databinding.ItemsCaroselBinding
import com.alexis.shop.domain.model.product.size.ProductSizeModel

class SizeSelectionAdapter(
    val context : Context,
    val items : ArrayList<ProductSizeModel>,
    val onClick : (ProductSizeModel) -> Unit
) : RecyclerView.Adapter<SizeSelectionAdapter.SizeSelectionHolder>(){

    private var sizeList = items

    fun updateData(data : ArrayList<ProductSizeModel>){
        sizeList.clear()
        sizeList.addAll(data)
        notifyDataSetChanged()
    }

    inner class SizeSelectionHolder(val binding : ItemsCaroselBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindItem(context: Context,sizeItem : ProductSizeModel){
            with(binding){
                itemText.text = sizeItem.name
                itemText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25f)
                when{
                   sizeItem.isSelected -> {
                       val face : Typeface = ResourcesCompat.getFont(context, R.font.alexis_gtwalsheim_regular )!!
                       setType(context, face, R.color.alexis_orange, 43f)
                   }
                    else -> {
                        val face: Typeface = ResourcesCompat.getFont(context, R.font.alexis_gtwalsheim_regular)!!
                        setType(context, face, R.color.grey_800, 35f)
                    }
                }
            }
        }
        fun setType(context: Context, face: Typeface, color: Int, size: Float) {
            binding.itemText.typeface = face
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.itemText.setTextColor(
                    context.resources.getColor(color, context.theme)
                )
            } else {
                binding.itemText.setTextColor(context.resources.getColor(color))
            }
            binding.itemText.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeSelectionHolder {
        val inflater = ItemsCaroselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SizeSelectionHolder(inflater)
    }

    override fun onBindViewHolder(holder: SizeSelectionHolder, position: Int) {
        val item = sizeList[position]
        holder.bindItem(context, item)
        holder.binding.root.setOnClickListener {
            val face: Typeface = ResourcesCompat.getFont(context, R.font.alexis_gtwalsheim_bold)!!
            holder.setType(context, face, R.color.alexis_orange, 43f)
            item.isSelected = true
            onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return sizeList.size
    }
}