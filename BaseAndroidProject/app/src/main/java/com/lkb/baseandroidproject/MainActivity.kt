package com.lkb.baseandroidproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.animation.ValueAnimator
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var isClicked = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val initialwidth = button.width
        button.setOnClickListener {
            isClicked = !isClicked
           if(isClicked){
               val widthAnimator = ValueAnimator.ofInt(button.width, 400)
               widthAnimator.addUpdateListener { animation ->
                   val animatedValue = animation.animatedValue as Int
                   button.layoutParams.width = animatedValue
                   button.requestLayout()
               }
               widthAnimator.start()
           }else{
               val widthAnimator = ValueAnimator.ofInt(400, 200)
               widthAnimator.addUpdateListener { animation ->
                   val animatedValue = animation.animatedValue as Int
                   button.layoutParams.width = animatedValue
                   button.requestLayout()
               }
               widthAnimator.start()
           }
        }
    }
}
