package com.alexis.shop.utils.animation

import android.view.animation.Interpolator

/**
 * Created by Uwais Alqadri on August 27, 2021
 */
class BounceInterpolator(
	private val amplitude: Double = 1.0,
	private val frequency: Double = 10.0
) : Interpolator {
	override fun getInterpolation(time: Float): Float {
		return (-1 * Math.pow(Math.E, -time / amplitude) *
		   Math.cos(frequency * time) + 1).toFloat()
	}
}