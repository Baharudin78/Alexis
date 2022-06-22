package com.alexis.shop.utils.common

import android.os.Handler
import android.os.Looper

fun withDelay(delay: Long = 500, block: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed(Runnable(block), delay)
}

fun withDelayTime(delay: Long, block: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed(Runnable(block), delay)
}