package com.alexis.shop.utils

import android.graphics.Color
import android.os.Build
import android.view.*

/**
 * Created by Uwais Alqadri on July 20, 2021
 */
object StatusBarUtil {
	fun hideStatusBar(window: Window?, darkText: Boolean) {
		if (window == null) return
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
		window.statusBarColor = Color.TRANSPARENT
		var flag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && darkText) {
			flag = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
		}
		window.decorView.systemUiVisibility = flag or
		   View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
	}

	fun showStatusBar(window: Window?) {
		if (window == null) return
		window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
	}

	fun statusBar(window: Window?, hide: Boolean) {
		if (window == null) return
		when(hide) {
			false -> {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
					with (window.insetsController!!) {
						show(WindowInsets.Type.statusBars())
						systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
					}
				} else {
					window.decorView.systemUiVisibility = (
					   View.SYSTEM_UI_FLAG_VISIBLE or
						  View.SYSTEM_UI_FLAG_LAYOUT_STABLE
					   )
				}
			}
			true -> {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
					with (window.insetsController!!) {
						hide(WindowInsets.Type.statusBars())
						systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
					}
				} else {
					window.decorView.systemUiVisibility = (
					   View.SYSTEM_UI_FLAG_FULLSCREEN or
						  View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
						  View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					   )
				}
			}
		}
	}

	fun forceStatusBar(window: Window?, hide: Boolean) {
		if (window == null) return
		if (hide) window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
		else window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
	}

	fun toggleStatusBar(window: Window?, hide: Boolean = true) {
		toggleStatusBar(window, hide, StatusMode.NORMAL)
	}

	fun toggleImmersiveStatusBar(window: Window?, hide: Boolean = true) {
		toggleStatusBar(window, hide, StatusMode.IMMERSIVE)
	}

	fun toggleStickImmersiveStatusBar(window: Window?, hide: Boolean = true) {
		toggleStatusBar(window, hide, StatusMode.STICKY)
	}

	private fun toggleStatusBar(window: Window?, hide: Boolean = true, statusMode: StatusMode = StatusMode.NORMAL) {
		window?.let {
			val flag: Int = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View
				.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
			when (statusMode) {
				StatusMode.IMMERSIVE -> if (hide) flag or View.SYSTEM_UI_FLAG_IMMERSIVE else flag
				StatusMode.STICKY -> if (hide) flag or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY else flag
				else -> flag
			}
			window.decorView.systemUiVisibility = if (hide) flag or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View
				.SYSTEM_UI_FLAG_FULLSCREEN else flag
		}
	}

	enum class StatusMode {
		NORMAL,
		IMMERSIVE,
		STICKY
	}
}