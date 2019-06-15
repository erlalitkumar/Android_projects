package com.lkb.baseandroidproject

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.property_value_layout.*

class PropertyValueAnimatorActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.property_value_layout)
        button2.setOnClickListener{
            val scaleX= PropertyValuesHolder.ofFloat(View.SCALE_X,0.5f,1f)
            val scaleY= PropertyValuesHolder.ofFloat(View.SCALE_Y,0.5f,1f)
            val alpha= PropertyValuesHolder.ofFloat(View.ALPHA,0f,1f)
            ObjectAnimator.ofPropertyValuesHolder(textView2,scaleX,scaleY,alpha).apply {
                interpolator = OvershootInterpolator()

            }.start()
        }

    }
}