package com.alexis.shop.utils.animation

import android.animation.ValueAnimator
import com.airbnb.lottie.LottieAnimationView

/**
 * Created by Uwais Alqadri on July 03, 2021
 */
class UpdateListener(private val lottieAnimationView: LottieAnimationView) :
	ValueAnimator.AnimatorUpdateListener {
//	override fun onAnimationUpdate(animation: ValueAnimator?) {
//		if (lottieAnimationView.frame > 0) {
//			lottieAnimationView.removeUpdateListener(this)
//			lottieAnimationView.setMinAndMaxFrame(0, 80)
//		}
//	}

	override fun onAnimationUpdate(animation: ValueAnimator) {
		if (lottieAnimationView.frame > 0) {
			lottieAnimationView.removeUpdateListener(this)
			lottieAnimationView.setMinAndMaxFrame(0, 80)
		}
	}
}