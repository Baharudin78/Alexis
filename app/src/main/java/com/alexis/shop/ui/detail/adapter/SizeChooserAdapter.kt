package com.alexis.shop.ui.detail.adapter

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.product.ProductsGetByIdSizeModel

class SizeChooserAdapter(
    private val context: Context,
    private val items: ArrayList<ProductsGetByIdSizeModel>,
    private val onClick: (ProductsGetByIdSizeModel) -> Unit
) : RecyclerView.Adapter<SizeChooserAdapter.SizeChooserViewHolder>() {
    private var sizeList = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeChooserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SizeChooserViewHolder(inflater, parent)
    }

    fun updateItems(data: ArrayList<ProductsGetByIdSizeModel>) {
        sizeList.clear()
        sizeList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SizeChooserViewHolder, position: Int) {
        val item = sizeList[position]

        holder.bind(context, item)

        holder.itemView.setOnClickListener {
            val face: Typeface = ResourcesCompat.getFont(context, R.font.alexis_gtwalsheim_bold)!!
            holder.setType(context, face, R.color.alexis_orange, 43f)
            item.isSelected = true
            onClick(item)
        }
    }

    override fun getItemCount(): Int = sizeList.size

    //Set the size text
    class SizeChooserViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_carousel, parent, false)) {
        var title: TextView = itemView.findViewById(R.id.item_text)

        fun bind(context: Context, item: ProductsGetByIdSizeModel) {
            title.text = item.name
            title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25f)

            when {
                item.isSelected -> {
                    val face: Typeface = ResourcesCompat.getFont(context, R.font.alexis_gtwalsheim_regular)!!
                    setType(context, face, R.color.alexis_orange, 43f)
                }
                else -> {
                    val face: Typeface = ResourcesCompat.getFont(context, R.font.alexis_gtwalsheim_regular)!!
                    setType(context, face, R.color.grey_800, 35f)
                }
            }
        }

        fun setType(context: Context, face: Typeface, color: Int, size: Float) {
            title.typeface = face
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                title.setTextColor(
                    context.resources.getColor(color, context.theme)
                )
            } else {
                title.setTextColor(context.resources.getColor(color))
            }
            title.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
        }
    }

}