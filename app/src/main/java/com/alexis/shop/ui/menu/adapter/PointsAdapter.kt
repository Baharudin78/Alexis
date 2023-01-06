package com.alexis.shop.ui.menu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.databinding.ItemPointsListBinding
import com.alexis.shop.domain.model.points.PointItemModel
import com.alexis.shop.utils.OnClickItem

class PointsAdapter (private val context: Context,
                     private val listener: OnClickItem
) : RecyclerView.Adapter<PointsAdapter.PointsViewHolder>() {

    private var pointsList: ArrayList<PointItemModel> = ArrayList()
    private var totalPoint = 0

    fun setData(data : List<PointItemModel>) {
        pointsList.clear()
        pointsList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointsViewHolder {
        val inflater = ItemPointsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PointsViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: PointsViewHolder, position: Int) {
        holder.bind(pointsList[position])
    }

    override fun getItemCount(): Int = pointsList.size

    inner class PointsViewHolder(val binding : ItemPointsListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item : PointItemModel) {
            binding.tvTanggal.text = item.createdAt
            binding.tvNama.text = item.name
            binding.tvPoint.text = item.point.toString()
            for (i in 0 until pointsList.size) {
                totalPoint += pointsList[i].point
                binding.tvTotalPoint.text = totalPoint.toString()
            }
            binding.root.setOnClickListener {
                listener.onClick(item)
            }
        }
    }
}