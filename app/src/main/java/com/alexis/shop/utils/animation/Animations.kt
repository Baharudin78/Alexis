package com.alexis.shop.utils.animation

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.alexis.shop.R
import com.alexis.shop.utils.gone

class Animations {

    companion object {

        const val ANIMATION_OUT = R.anim.animate_entrace_fragment
        const val ANIMATION_IN = R.anim.to_top_left

        fun runAnimation(context: Context, animationType: Int, index : Int?, vararg views: View) {

            var pos = index ?: views.size

            for (view in views) {
                val anim: Animation = if (animationType == ANIMATION_IN) {
                    AnimationUtils.loadAnimation(context, R.anim.animate_entrace_fragment)
                } else {
                    AnimationUtils.loadAnimation(context, R.anim.to_top_left)
                }

                anim.duration = ((pos * 7) * 50).toLong()
                view.startAnimation(anim)

                if (animationType == ANIMATION_OUT){
                    anim.setAnimationListener(object : Animation.AnimationListener{
                        override fun onAnimationStart(animation: Animation?) {

                        }

                        override fun onAnimationEnd(animation: Animation?) {
                            view.gone()
                        }

                        override fun onAnimationRepeat(animation: Animation?) {

                        }
                    })
                }

                if (pos != 1) {
                    pos -= 1
                }
            }
        }
    }
}