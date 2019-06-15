package com.lkb.baseandroidproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.animation.ValueAnimator
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var isClicked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            isClicked = !isClicked
            if (isClicked) {
                inAndOutLinearAnimation(60f, 0f)
            } else {
                inAndOutLinearAnimation(0f, 60f)
            }
        }
    }

    private fun inAndOutLinearAnimation(param1: Float, param2: Float) {
        val widthAnimator = ValueAnimator.ofFloat(param1, param2)
        widthAnimator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Float
            iconContainer.translationX = animatedValue
            widthAnimator.duration = 500
        }
        widthAnimator.start()
    }
}
