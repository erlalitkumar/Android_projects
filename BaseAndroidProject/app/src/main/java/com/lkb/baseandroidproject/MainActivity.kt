package com.lkb.baseandroidproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.animation.ValueAnimator
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var isClicked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val initialx = button.x
        button.setOnClickListener {
            isClicked = !isClicked
           if(isClicked){
               val widthAnimator = ValueAnimator.ofFloat(initialx, 400f)
               widthAnimator.addUpdateListener { animation ->
                   val animatedValue = animation.animatedValue as Float
                   button.translationX = animatedValue
                   widthAnimator.duration = 500
               }
               widthAnimator.start()
           }else{
               val widthAnimator = ValueAnimator.ofFloat(400f, initialx)
               widthAnimator.addUpdateListener { animation ->
                   val animatedValue = animation.animatedValue as Float
                   button.translationX = animatedValue
                   widthAnimator.duration = 500
               }
               widthAnimator.start()
           }
        }
    }
}
