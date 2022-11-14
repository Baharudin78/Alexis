package com.alexis.shop.ui.shopping_bag.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.shoppingbag.ShoppingBagModel
import com.alexis.shop.utils.*
import com.alexis.shop.utils.animation.Animations
import com.alexis.shop.utils.common.withDelay

class ShoppingBagAdapter (
    private val context: Context,
    private val listener: OnShoppingBagClickItem
    ) : RecyclerView.Adapter<ShoppingBagViewHolder>() {

    private var contactList: ArrayList<ShoppingBagModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingBagViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ShoppingBagViewHolder(inflater, parent)
    }

    fun setData(data: List<ShoppingBagModel>) {
        contactList.clear()
        contactList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ShoppingBagViewHolder, position: Int) {
        val item: ShoppingBagModel = contactList[position]
        val motion = holder.itemView.findViewById<MotionLayout>(R.id.parent)

        Animations.runAnimation(
            holder.itemView.context,
            Animations.ANIMATION_IN,
            position,
            holder.itemView
        )

        holder.bind(item)

        holder.itemView.findViewById<ImageView>(R.id.btn_1)
            .setOnClickListener {
                motion.transitionToEnd()
                withDelay(1000) {
                    listener.onDeleteItem(item)
                    withDelay(300) { resetTransition(motion) }
                }
            }

        holder.itemView.findViewById<ImageView>(R.id.btn_2)
            .setOnClickListener {
                move2Wishlist(
                        it.context,
                        holder.itemView.findViewById<ConstraintLayout>(R.id.allbackgr),
                        item,
                        listener
                )
            }

        holder.itemView.findViewById<ImageView>(R.id.btn_3)
            .setOnClickListener { listener.onEditItem(item) }
    }

    private fun resetTransition(motionLayout: MotionLayout?) {
        motionLayout?.let {
            it.progress = 0f
            it.setTransition(R.id.start, R.id.first_bounce)
        }
    }

    private fun move2Wishlist(context: Context, rowView: View, item: ShoppingBagModel, listener: OnShoppingBagClickItem) {
        val anim: Animation = AnimationUtils.loadAnimation(
            context, R.anim.to_top_right
        )
        anim.duration = 800
        rowView.startAnimation(anim)
        withDelay(anim.duration) {
            listener.onMove2WishList(item)
        }
    }

    override fun getItemCount(): Int = contactList.size

}