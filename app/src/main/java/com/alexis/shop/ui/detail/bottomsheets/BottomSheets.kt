package com.alexis.shop.ui.detail.bottomsheets

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.alexis.shop.R
import com.alexis.shop.utils.customTopBarsColor
import com.alexis.shop.utils.transparentNavBar
import com.alexis.shop.utils.whiteNavBar
import com.google.android.material.bottomsheet.BottomSheetDialog

fun TestingUntukMyAccount(activity: Activity, tipe: Int) {
    val bottomsheet = BottomSheetDialog(
            activity,
            R.style.AppBottomSheetDialogTheme
        )
    bottomsheet.window?.setDimAmount(0f)
    val btsheetview: View = LayoutInflater.from(activity).inflate(
        R.layout.bottomsheet_sample_lorem_ipsum,
        bottomsheet.findViewById(R.id.bottomsheet_sizer)
    )
    val title = btsheetview.findViewById<TextView>(R.id.title)
    val image =
        btsheetview.findViewById<ImageView>(R.id.image)
    if (tipe == 0) {
        title.text = "Delivery"
        image.setImageDrawable(
            ContextCompat.getDrawable(
                activity,
                R.drawable.ic_delivery_menu
            )
        )
    } else {
        title.text = "Return"
        image.setImageDrawable(
            ContextCompat.getDrawable(
                activity,
                R.drawable.ic_return_menu
            )
        )
    }
    image.setColorFilter(ContextCompat.getColor(activity, R.color.black))
    bottomsheet.setContentView(btsheetview)
    bottomsheet.show()
}


fun bottomsheetSizeInfo(activity: Activity) {
    val bottomsheet =
        BottomSheetDialog(
            activity,
            R.style.AppBottomSheetDialogTheme
        )
    bottomsheet.window?.setDimAmount(0f)
    val btsheetview = LayoutInflater.from(activity).inflate(
        R.layout.bottomsheet_size_indicator,
        bottomsheet.findViewById(R.id.bottomsheet_sizer)
    )
    bottomsheet.setContentView(btsheetview)
    bottomsheet.show()
}