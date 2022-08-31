package com.alexis.shop.ui.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.alexis.shop.R
import com.alexis.shop.ui.main.MainActivity
import com.alexis.shop.utils.customTopBarsColor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.splash_lottie_activity.*

@AndroidEntryPoint
class SplashLottieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_lottie_activity)

        customTopBarsColor(true)

        //Implement Animation Splash
        animationView.apply {
            setAnimation(R.raw.data)
            playAnimation()
            scaleType = ImageView.ScaleType.FIT_XY
        }

        animationView.addAnimatorListener(object : Animator.AnimatorListener {
            //            override fun onAnimationStart(animation: Animator?) {
//            }
//
//            //Go to MainActivity when Splash Animation's end
//            override fun onAnimationEnd(animation: Animator?) {
//                val intent = Intent(this@SplashLottieActivity, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//
//                overridePendingTransition(0, R.anim.activity_out_main)
//            }
//
//            override fun onAnimationCancel(animation: Animator?) {
//            }
//
//            override fun onAnimationRepeat(animation: Animator?) {
//            }
            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator) {
                val intent = Intent(this@SplashLottieActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }
        })
    }
}