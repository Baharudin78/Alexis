package com.alexis.shop.utils

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.TextUtilsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.LifecycleOwner
import com.alexis.shop.R
import com.skydoves.balloon.*
import com.skydoves.balloon.BalloonSizeSpec.WRAP
import java.util.*
import kotlin.collections.HashMap

fun AppCompatActivity.popUpOnTop(text: String): Balloon{
    return this.popUpOnTop(this, text)
}

fun Context.popUpOnTop(lifecycleOwner: LifecycleOwner, text: String): Balloon{
    return Balloon.Builder(this)
        .setArrowSize(10)
        .setArrowOrientation(ArrowOrientation.TOP)
        .setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
        .setArrowPosition(0.5f)
        .setWidth(WRAP)
        .setHeight(WRAP)
        .setTextSize(12f)
        .setCornerRadius(10f)
        .setAlpha(0.9f)
        .setText(text)
        .setTextIsHtml(true)
        .setPadding(10)
        .setTextColor(ContextCompat.getColor(this, R.color.white))
        .setTextIsHtml(true)
        .setBackgroundColor(ContextCompat.getColor(this, R.color.black))
        .setBalloonAnimation(BalloonAnimation.FADE)
        .setLifecycleOwner(lifecycleOwner)
        .setAutoDismissDuration(2000L)
        .build()
}

fun AppCompatActivity.popUpOnBottom(text: String): Balloon{
    return popUpOnBottom(this, text)
}

fun LifecycleOwner.popUpOnBottom(context: Context, text: String): Balloon{
    return Balloon.Builder(context)
        .setArrowSize(10)
        .setArrowOrientation(ArrowOrientation.BOTTOM)
        .setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
        .setArrowPosition(0.5f)
        .setWidth(WRAP)
        .setHeight(WRAP)
        .setTextSize(12f)
        .setCornerRadius(10f)
        .setAlpha(0.9f)
        .setText(text)
        .setTextIsHtml(true)
        .setPadding(10)
        .setTextColor(ContextCompat.getColor(context, R.color.white))
        .setTextIsHtml(true)
        .setBackgroundColor(ContextCompat.getColor(context, R.color.black))
        .setBalloonAnimation(BalloonAnimation.FADE)
        .setLifecycleOwner(this)
        .setAutoDismissDuration(2000L)
        .build()
}

fun LifecycleOwner.popUpOnBottom(context: Context, text: String, marginLeft: Int, marginRight: Int): Balloon{
    return Balloon.Builder(context)
        .setArrowSize(10)
        .setArrowOrientation(ArrowOrientation.BOTTOM)
        .setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
        .setArrowPosition(0.5f)
        .setWidth(WRAP)
        .setHeight(WRAP)
        .setTextSize(12f)
        .setCornerRadius(10f)
        .setAlpha(0.9f)
        .setMarginLeft(marginLeft)
        .setMarginRight(marginRight)
        .setText(text)
        .setTextIsHtml(true)
        .setPadding(10)
        .setTextColor(ContextCompat.getColor(context, R.color.white))
        .setTextIsHtml(true)
        .setBackgroundColor(ContextCompat.getColor(context, R.color.black))
        .setBalloonAnimation(BalloonAnimation.FADE)
        .setLifecycleOwner(this)
        .setAutoDismissDuration(2000L)
        .build()
}

fun AppCompatActivity.popUpUsingLayout(whichIsClicked: Int, param: HashMap<String, String>): Balloon{
    return popUpUsingLayout(this, whichIsClicked, param)
}

fun LifecycleOwner.popUpUsingLayout(context: Context, whichIsClicked: Int, param: HashMap<String, String>): Balloon{
    return Balloon.Builder(context)
        .setLayout(layoutChooser(whichIsClicked, param))
        .setWidth(WRAP)
        .setHeight(WRAP)
        .setArrowOrientation(ArrowOrientation.BOTTOM)
        .setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
        .setArrowPosition(0.5f)
        .setArrowSize(10)
        .setTextSize(12f)
        .isRtlSupport(isRtlLayout())
        .setCornerRadius(10f)
        .setMarginRight(12)
        .setElevation(6)
        .setPadding(5)
        .setBackgroundColorResource(android.R.color.black)
        .setBalloonAnimation(BalloonAnimation.FADE)
        .setIsVisibleOverlay(true)
        .setDismissWhenShowAgain(true)
        .setDismissWhenTouchOutside(false)
        .setDismissWhenOverlayClicked(false)
        .setLifecycleOwner(this)
        .setAutoDismissDuration(2000L)
        .build()
}

val TOTAL_SAVINGS = 0
val TOTAL_WEIGHT = 1
fun layoutChooser(whichIsClicked: Int, param: HashMap<String, String>): Int {
    return when (whichIsClicked){
        TOTAL_SAVINGS -> R.layout.pop_up_total_savings
        TOTAL_WEIGHT -> R.layout.pop_up_total_weight
        else -> R.layout.pop_up_unknown
    }
}

fun isRtlLayout(): Boolean {
    return TextUtilsCompat.getLayoutDirectionFromLocale(
            Locale.getDefault()
    ) == ViewCompat.LAYOUT_DIRECTION_RTL
}

