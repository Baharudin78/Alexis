package com.alexis.shop.utils.circle_layout_manager

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs
import kotlin.math.cos

/**
 * Created by Uwais Alqadri on August 12, 2021
 */
class CircularHorizontalMode(
	circleOffset: Int = 300, // curve/circle size
	degToRad: Float = 1.0f / 180.0f * Math.PI.toFloat(),
	scalingRatio: Float =  0.0007f, // item scale
	translationRatio: Float = 0.14f
) : ItemViewMode {
	private var mCircleOffset = circleOffset
	private var mDegToRad = degToRad
	private var mScalingRatio = scalingRatio
	private var mTranslationRatio = translationRatio

	override fun applyToView(view: View?, parent: RecyclerView?) {
		if (view == null) return
		val halfWidth = view.width * 0.5f
		val parentHalfWidth: Float = (parent?.width ?: 0) * 0.5f
		val x = view.x
		val rotation = parentHalfWidth - halfWidth - x
		view.pivotY = 0.0f
		view.pivotX = halfWidth
		view.rotation = -rotation * 0.05f
		view.translationY = (-cos((rotation * mTranslationRatio * mDegToRad).toDouble()) + 1).toFloat() * mCircleOffset
		val scale = 1.0f - abs(parentHalfWidth - halfWidth - x) * mScalingRatio
		view.scaleX = scale
		view.scaleY = scale
	}
}