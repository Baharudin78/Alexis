package com.alexis.shop.utils.circle_layout_manager

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R

/**
 * Created by Uwais Alqadri on August 12, 2021
 */
class CircleRecyclerView @JvmOverloads constructor(
	context: Context,
	@Nullable attrs: AttributeSet? = null,
	defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle), View.OnClickListener {

	private var viewMode: ItemViewMode? = null

	fun setViewMode(mode: ItemViewMode?) {
		viewMode = mode
	}

	override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
		super.onLayout(changed, l, t, r, b)
		val layoutManager = layoutManager as LinearLayoutManager
		if (layoutManager.canScrollHorizontally()) setPadding(
			width / 2,
			0,
			width / 2,
			0
		)
		clipToPadding = false
		clipChildren = false
	}

	override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
		super.onScrollChanged(l, t, oldl, oldt)
		if (viewMode != null) {
			val count: Int = childCount
			(0 until count).forEach { item ->
				val view = getChildAt(item)
				viewMode?.applyToView(view, this)
			}
		}
	}

	override fun requestLayout() {
		super.requestLayout()
		if (viewMode != null && layoutManager != null) {
			val count: Int = childCount
			(0 until count).forEach {
				val view = getChildAt(it)
				viewMode?.applyToView(view, this)
			}
		}
	}

	override fun onClick(v: View?) = Unit

	companion object {
		private const val TAG = "CircleRecyclerView"
	}
}