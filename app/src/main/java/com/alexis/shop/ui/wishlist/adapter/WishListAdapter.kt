package com.alexis.shop.ui.wishlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.wishlist.WishlistModel
import com.alexis.shop.utils.animation.Animations
import com.alexis.shop.utils.common.withDelay
import com.alexis.shop.utils.OnWishlistClickItem

class WishListAdapter: RecyclerView.Adapter<WishListViewHolder>() {

    private var wishlistData: ArrayList<WishlistModel> = ArrayList()
    private var listener: OnWishlistClickItem? = null

    fun setData(items: List<WishlistModel>) {
        wishlistData.clear()
        wishlistData.addAll(items)
        notifyDataSetChanged()
    }

    fun setListener(listener: OnWishlistClickItem) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return WishListViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: WishListViewHolder, position: Int) {
        val item : WishlistModel = wishlistData[position]
        val motion = holder.itemView.findViewById<MotionLayout>(R.id.parent)

//        Animations.runAnimation(
//            holder.itemView.context,
//            Animations.ANIMATION_IN,
//            position,
//            holder.itemView
//        )

        holder.bind(item)

        holder.itemView.findViewById<ImageView>(R.id.btn2delete)
                .setOnClickListener {
                    motion.transitionToEnd()
                    withDelay(1000) {
                        listener?.onDeleteItem(item)
                        notifyDataSetChanged()
                        withDelay(300) { resetTransition(motion) }
                    }
                }

        holder.itemView.findViewById<ImageView>(R.id.add2cart)
                .setOnClickListener { listener?.onMove2BasketClick(item) }
    }

    override fun getItemCount(): Int = wishlistData.size

    private fun resetTransition(motionLayout: MotionLayout?) {
        motionLayout?.let {
            it.progress = 0f
            it.setTransition(R.id.start, R.id.first_bounce)
        }
    }

}