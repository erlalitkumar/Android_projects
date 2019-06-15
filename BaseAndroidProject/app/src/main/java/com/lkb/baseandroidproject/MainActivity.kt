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
        val initialx = searchImage.x
        val xofClock = clockImage.x
        val delta = initialx-xofClock
        button.setOnClickListener {
            isClicked = !isClicked
           if(isClicked){
               val widthAnimator = ValueAnimator.ofFloat(60f, 0f)
               widthAnimator.addUpdateListener { animation ->
                   val animatedValue = animation.animatedValue as Float
                   iconContainer.translationX = animatedValue
                   widthAnimator.duration = 500
               }
               widthAnimator.start()
           }else{
               val widthAnimator = ValueAnimator.ofFloat(0f, 60f)
               widthAnimator.addUpdateListener { animation ->
                   val animatedValue = animation.animatedValue as Float
                   iconContainer.translationX = animatedValue
                   widthAnimator.duration = 500
               }
               widthAnimator.start()
           }
        }
    }
}
