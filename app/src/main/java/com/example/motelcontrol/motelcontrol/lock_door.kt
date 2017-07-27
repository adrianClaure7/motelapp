package com.example.motelcontrol.motelcontrol

import android.animation.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.motelcontrol.motelcontrol.rest.RestCalls
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.RelativeLayout
import com.example.motelcontrol.motelcontrol.viewclasess.Circle

class lock_door : AppCompatActivity() {
    private val rest = RestCalls()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock_door)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)


        executeLoading()

    }

    fun executeLoading() {
        var loadingCircle1 = findViewById<Circle>(R.id.loading_circle1)
        var loadingCircle2 = findViewById<Circle>(R.id.loading_circle2)
        var loadingCircle3 = findViewById<Circle>(R.id.loading_circle3)
        var loadingCircle4 = findViewById<Circle>(R.id.loading_circle4)
        var mFrameLayout = findViewById<RelativeLayout>(R.id.main_layout)

        val circleAnimation1 = getAnimationToCircle(loadingCircle1)
        val circleAnimation2 = getAnimationToCircle(loadingCircle2)
        val circleAnimation3 = getAnimationToCircle(loadingCircle3)
        val circleAnimation4 = getAnimationToCircle(loadingCircle4)

        mFrameLayout.setOnClickListener(View.OnClickListener {
            view: View? ->
                val animatorSet = AnimatorSet()
                animatorSet.play(circleAnimation4).after(circleAnimation3)
                animatorSet.play(circleAnimation3).after(circleAnimation2)
                animatorSet.play(circleAnimation2).after(circleAnimation1)
                animatorSet.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        animatorSet.start()
                    }
                })
                animatorSet.setDuration(500)
                animatorSet.start()
        })
    }

    fun getAnimationToCircle(circle: Circle): Animator {
        val valueAnimator = ValueAnimator.ofFloat(1f, 1.2f)//2
        valueAnimator.addUpdateListener({ animation ->
            //3
            val value = animation.animatedValue as Float
            //4
            circle.setScaleX(value)
            circle.setScaleY(value)
        })

        valueAnimator.interpolator = LinearInterpolator()
        return valueAnimator
    }
}