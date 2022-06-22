package com.alexis.shop.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import com.alexis.shop.ui.main.MainActivity

/**
 * Created by Uwais Alqadri on August 08, 2021
 */
@Suppress("DEPRECATION")
class VibrateUtil(context: Context) {

	private val service = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
	private val vibrateTime = 52L
	private val vibrateStrength = 45

	fun single() {
		when {
			Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
				service.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK))
			}
			Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
				service.vibrate(VibrationEffect.createOneShot(vibrateTime, vibrateStrength))
			}
			else -> {
				service.vibrate(vibrateTime)
			}
		}
	}

	fun done() {
		when {
			Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
				service.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK))
			}
			Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
				service.vibrate(VibrationEffect.createOneShot(vibrateTime, vibrateStrength))
			}
			else -> {
				service.vibrate(vibrateTime)
			}
		}
	}

	fun error() {
		when {
			Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
				service.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK))
			}
			Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
				service.vibrate(VibrationEffect.createOneShot(vibrateTime, vibrateStrength))
			}
			else -> {
				service.vibrate(vibrateTime)
			}
		}
	}
}