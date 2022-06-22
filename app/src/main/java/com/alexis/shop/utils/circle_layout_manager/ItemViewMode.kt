package com.alexis.shop.utils.circle_layout_manager

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Uwais Alqadri on August 12, 2021
 */
interface ItemViewMode {
	fun applyToView(view: View?, parent: RecyclerView?)
}