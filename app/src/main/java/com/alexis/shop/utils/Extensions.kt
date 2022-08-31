package com.alexis.shop.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.os.Build
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.lottie.LottieAnimationView
import com.alexis.shop.R
import com.alexis.shop.ui.menu.MenuFragment
import com.alexis.shop.utils.animation.BounceInterpolator
import com.alexis.shop.utils.animation.UpdateListener
import com.alexis.shop.utils.common.withDelay
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.textfield.TextInputEditText
import com.ortiz.touchview.TouchImageView
import java.text.SimpleDateFormat
import java.util.*


fun log(content : String?){
    Log.d("AlexisLog", content.toString())
}

fun Context.toast(content : String?){
    try {
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show()
    }catch (e: Exception){
        e.printStackTrace()
        log("https error back: " + e.localizedMessage)
    }
}

 fun View.animateGone() {
    animate()
        .alpha(0f)
        .setDuration(500)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                visibility = View.GONE
            }
//            override fun onAnimationEnd(animation: Animator?) {
//                visibility = View.GONE
//            }
        })
}

fun View.animateVisible() {
    animate()
        .alpha(1f)
        .setDuration(500)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                visibility = VISIBLE
            }
//            override fun onAnimationEnd(animation: Animator?) {
//                visibility = VISIBLE
//            }
        })
}

fun ImageView.setImageDrawableWithAnimation(drawable: Drawable, duration: Int = 300) {
    val currentDrawable = getDrawable()
    if (currentDrawable == null) {
        setImageDrawable(drawable)
        return
    }

    val transitionDrawable = TransitionDrawable(arrayOf(
        currentDrawable,
        drawable
    ))
    setImageDrawable(transitionDrawable)
    transitionDrawable.startTransition(duration)
    transitionDrawable.isCrossFadeEnabled = true
}

fun TextInputEditText.errorSignEditText(lifeCycleOwner: LifecycleOwner){
    this.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_red_warn_sign, 0)
    this.addTextChangedListener {
        if(it.toString() != ""){
            this.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        }
    }
    this.requestFocus()
    lifeCycleOwner.popUpOnBottom(context, "not valid").showAlignTop(this,430,0)
}

fun ViewPager2.reduceDragSensitivity(factor: Int = 6) {
    val recyclerViewField = ViewPager2::class.java.getDeclaredField("mRecyclerView")
    recyclerViewField.isAccessible = true
    val recyclerView = recyclerViewField.get(this) as RecyclerView

    val touchSlopField = RecyclerView::class.java.getDeclaredField("mTouchSlop")
    touchSlopField.isAccessible = true
    val touchSlop = touchSlopField.get(recyclerView) as Int
    touchSlopField.set(recyclerView, touchSlop * factor)
}

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .centerInside()
        .into(this)

    log(url)
}

fun TouchImageView.loadTouchImage(url: String, onResourceReady: () -> Unit) {
    Glide.with(this)
        .load(url)
        .into(object : CustomTarget<Drawable?>() {
            @SuppressLint("SetTextI18n")
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable?>?) {
                setImageDrawable(resource)
                onResourceReady()
                Log.d("GlideExampleActivity","loaded within ${System.currentTimeMillis() - System.currentTimeMillis()} ms")
            }

            override fun onLoadCleared(placeholder: Drawable?) = Unit
        })
}

fun Context.toast(content : String?, type: Int) {
    try {
        Toast.makeText(this,content, type).show()
    } catch (e: Exception) {
        e.printStackTrace()
        log("https error back: " + e.localizedMessage)
    }
}

fun Context.savedCountToPrefs(count: Int) {
    val sharedPreferences: SharedPreferences = this.getSharedPreferences("CART_PREFS", Context.MODE_PRIVATE)
    sharedPreferences.edit().apply {
        putInt("COUNT", count)
        apply()
    }
}

fun Context.getSavedPrefsCount() : Int {
    val sharedPreferences: SharedPreferences = this.getSharedPreferences("CART_PREFS", Context.MODE_PRIVATE)
    return sharedPreferences.getInt("COUNT", 0)
}

fun Context.savedWishToPrefs(count: Int) {
    val sharedPreferences: SharedPreferences = this.getSharedPreferences("WISH_PREFS", Context.MODE_PRIVATE)
    sharedPreferences.edit().apply {
        putInt("COUNT", count)
        apply()
    }
}

fun Context.getSavedPrefsWish() : Int {
    val sharedPreferences: SharedPreferences = this.getSharedPreferences("WISH_PREFS", Context.MODE_PRIVATE)
    return sharedPreferences.getInt("COUNT", 0)
}

fun View.setPushClickListener(onClick: () -> Unit) {
    this.setOnClickListener {
        val anim = AnimationUtils.loadAnimation(context, R.anim.zoom_in)
        anim.interpolator = BounceInterpolator()
        this.startAnimation(anim)

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) = Unit

            override fun onAnimationEnd(animation: Animation?) {
                onClick()
            }

            override fun onAnimationRepeat(animation: Animation?) = Unit

        })
    }
}

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.invisible(){
    visibility = View.INVISIBLE
}

fun View.gone(){
    visibility = View.GONE
}

fun View.motionVisibility(visibility: Int) {
    val motionLayout = parent as MotionLayout
    motionLayout.constraintSetIds.forEach {
        val constraintSet = motionLayout.getConstraintSet(it) ?: return@forEach
        constraintSet.setVisibility(this.id, visibility)
        constraintSet.applyTo(motionLayout)
    }
}

fun LottieAnimationView.resetLottieAnimation() {
    with(this) {
        setMinAndMaxProgress(0f, 1f)
        frame = 0
        addAnimatorUpdateListener(UpdateListener(this))
        playAnimation()
    }
}

fun getCurrentDate(): String {
    val calendar = Calendar.getInstance()
    val mdformat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

    return mdformat.format(calendar.time)
}

fun stringToDate(date: String, format: String): Date {
    val formater = SimpleDateFormat(format, Locale.getDefault())
    val spDate = formater.parse(date)

    return spDate
}

fun yesterday(): Date? {
    val cal = Calendar.getInstance()
    cal.add(Calendar.DATE, -1)
    return cal.time
}

fun EditText.setReadOnly(value: Boolean, inputType: Int = InputType.TYPE_NULL) {
    isFocusable = !value
    isFocusableInTouchMode = !value
    this.inputType = inputType
}

fun View.setMeasure(){
    this.measure(
            View.MeasureSpec.makeMeasureSpec(
                    0,
                    View.MeasureSpec.UNSPECIFIED
            ),
            View.MeasureSpec.makeMeasureSpec(
                    0,
                    View.MeasureSpec.UNSPECIFIED
            )
    )
    this.layout(0, 0, this.measuredWidth, this.measuredHeight)
}

fun Fragment.animateOut(fragmentManager: FragmentManager) {
    val trans = AnimationUtils.loadAnimation(context, R.anim.animate_quit_fragment)
    trans.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {
        }

        override fun onAnimationRepeat(animation: Animation) {
        }

        override fun onAnimationEnd(animation: Animation) {
            try {
                justOut()
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    })
    view?.startAnimation(trans)
    this.justOut()
}

fun Fragment.justOut() {
//    requireActivity().showStatusBar()
//    requireActivity().customTopBarsColor(true)
    requireActivity().supportFragmentManager.apply {
        commit {
            popBackStackImmediate("shopping_bag_fragments", FragmentManager.POP_BACK_STACK_INCLUSIVE)
            popBackStackImmediate("menu_fragments", FragmentManager.POP_BACK_STACK_INCLUSIVE)
            popBackStackImmediate("wishlist_fragments", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}

fun Fragment.handleBackPressed() {
    requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            requireActivity().supportFragmentManager.popBackStack()
        }
    })
}

fun FragmentManager.menuNavigator(fragment: Fragment) {
    commit {
//        setCustomAnimations(
//            R.anim.exit_right_to_left,
//            R.anim.exit_right_to_left,
//            R.anim.exit_right_to_left,
//            R.anim.exit_left_to_right
//        )
        setReorderingAllowed(true)
        replace(R.id.transparent_menu, fragment)
        addToBackStack("menu_fragments")
    }
}

fun FragmentManager.accountNavigator(fragment: Fragment) {
    commit {
//        setCustomAnimations(
//            R.anim.exit_right_to_left,
//            R.anim.exit_right_to_left,
//            R.anim.exit_right_to_left,
//            R.anim.exit_left_to_right
//        )
        setReorderingAllowed(true)
        replace(R.id.transparent_menu, fragment)
        addToBackStack("menu_fragments")
    }
}

fun FragmentManager.shopNavigator(fragment: Fragment) {
    commit {
//        setCustomAnimations(
//            R.anim.enter_right_to_left,
//            R.anim.exit_right_to_left,
//            R.anim.enter_right_to_left,
//            R.anim.exit_left_to_right
//        )
        setReorderingAllowed(true)
        replace(R.id.transparent_menu, fragment)
        addToBackStack("shopping_bag_fragments")
    }
}

fun FragmentManager.wishlistNavigator(fragment: Fragment) {
    commit {
        setCustomAnimations(
            R.anim.enter_right_to_left,
            R.anim.exit_right_to_left,
            R.anim.enter_right_to_left,
            R.anim.exit_left_to_right
        )
        setReorderingAllowed(true)
        replace(R.id.transparent_menu, fragment)
        addToBackStack("wishlist_fragments")
    }
}

fun Activity.hideSoftKeyboard() {
    val inputMethodManager: InputMethodManager = this.getSystemService(
        Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    if (inputMethodManager.isAcceptingText) {
        inputMethodManager.hideSoftInputFromWindow(
            this.currentFocus!!.windowToken, 0
        )
    }
}

fun EditText.passwordIsVisible(visible: Boolean) {
    when(visible) {
        true -> {
            this.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }
        false -> {
            this.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }
}

fun Activity.hideNavBar(){
    /*val flags = *//*View.SYSTEM_UI_FLAG_LAYOUT_STABLE or*//*
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    window.decorView.systemUiVisibility = flags
    val decorView = window.decorView
    decorView.setOnSystemUiVisibilityChangeListener { visibility ->
        if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
            decorView.systemUiVisibility = flags
        }
    }*/
    this.customTopBarsColor(false)
}

fun Activity.customTopBarsColor(isBlack: Boolean, window: Window = this.window) {
    //Make top bar (signal, battery) and menu bar transparent
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(applicationContext, android.R.color.transparent)
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.decorView.systemUiVisibility = when (isBlack) {
            true ->{
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            false ->{
                window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        }
    }
}

fun Activity.whiteNavBar(){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.navigationBarColor = resources.getColor(R.color.white, this.theme)
    }
}

fun Activity.transparentNavBar(){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.navigationBarColor = resources.getColor(R.color.transparent_80percent, this.theme)
    }
}

fun getWidthResolution(context: Context): Int {
    val metrics = DisplayMetrics()
    var width = 0
    (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager)
        .defaultDisplay.getRealMetrics(metrics)
    width = metrics.widthPixels
    return width
}

fun getOneXMeters(context: Context): Int {
    val metrics = DisplayMetrics()
    var width = 0
    (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager)
        .defaultDisplay.getRealMetrics(metrics)
    width = metrics.widthPixels
    return width/6
}

fun getHeightResolution(context: Context): Int {
    val metrics = DisplayMetrics()
    var height = 0
    (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager)
        .defaultDisplay.getRealMetrics(metrics)
    height = metrics.heightPixels
    return height/(125/10)
}

fun getTrullyHeightResolution(context: Context): Int {
    val metrics = DisplayMetrics()
    var height = 0
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager)
            .defaultDisplay.getRealMetrics(metrics)
        height = metrics.heightPixels
    } else {
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager)
            .defaultDisplay.getMetrics(metrics)
        height = metrics.heightPixels
    }
    return height
}

fun removeFragment(comAct: AppCompatActivity) {
    val fragmentManager: FragmentManager = comAct.supportFragmentManager
    val myFragment = fragmentManager.findFragmentByTag("MY_FRAGMENT")
    myFragment?.animateOut(fragmentManager)
}

inline fun MotionLayout.addTransitionListener(
    crossinline onTransitionStarted: (startId: Int, endId: Int) -> Unit = { _, _ -> },
    crossinline onTransitionChange: (startId: Int, endId: Int, progress: Float) -> Unit = { _, _, _ -> },
    crossinline onTransitionCompleted: (currentId: Int) -> Unit = {},
    crossinline onTransitionTrigger: (triggerId: Int, positive: Boolean, progress: Float) -> Unit = { _, _, _ -> }
): MotionLayout.TransitionListener {
    return object : MotionLayout.TransitionListener {

        override fun onTransitionStarted(ml: MotionLayout, startId: Int, endId: Int) {
            onTransitionStarted(startId, endId)
        }

        override fun onTransitionChange(ml: MotionLayout, startId: Int, endId: Int, progress: Float) {
            onTransitionChange(startId, endId, progress)
        }

        override fun onTransitionCompleted(ml: MotionLayout, currentId: Int) {
            onTransitionCompleted(currentId)
        }

        override fun onTransitionTrigger(
            ml: MotionLayout,
            triggerId: Int,
            positive: Boolean,
            progress: Float
        ) {
            onTransitionTrigger(triggerId, positive, progress)
        }

    }
        .also(::addTransitionListener)
}

fun View.animateOpen(id: Int) {
    gone()
    val anim = AnimationUtils.loadAnimation(
        context,
        R.anim.animate_entrace_fragment
    ).apply {
        duration = 1000
    }

    withDelay((MenuFragment.animateEntraceMenuTime * id).toLong()) {
        startAnimation(anim)
    }

    anim.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) = Unit

        override fun onAnimationEnd(animation: Animation?) {
            visible()
        }

        override fun onAnimationRepeat(animation: Animation?) = Unit

    })
}

fun View.animateClose(reverseId: Int) {
    visible()
    val anim = AnimationUtils.loadAnimation(
        context,
        R.anim.animate_quit_fragment).apply {
        duration = 1000
    }

    withDelay((MenuFragment.animateEntraceMenuTime * reverseId).toLong()) {
        startAnimation(anim)
    }

    anim.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) = Unit

        override fun onAnimationEnd(animation: Animation?) {
            gone()
        }

        override fun onAnimationRepeat(animation: Animation?) = Unit

    })
}

fun Int?.orZero():Int = this ?: 0

fun Boolean?.orFalse():Boolean = this ?: false

abstract class DoubleClickListener : View.OnClickListener {
    var lastClickTime: Long = 0
    override fun onClick(v: View?) {
        val clickTime = System.currentTimeMillis()
        if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
            onDoubleClick(v)
        }
        lastClickTime = clickTime
    }

    abstract fun onDoubleClick(v: View?)

    companion object {
        private const val DOUBLE_CLICK_TIME_DELTA: Long = 300 //milliseconds
    }
}