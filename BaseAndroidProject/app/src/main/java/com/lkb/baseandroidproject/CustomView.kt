package com.lkb.baseandroidproject

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.global_offer_view_ab.view.*


class CustomView : FrameLayout {
    constructor(context: Context) : super(context) {
        initViews(null)
    }


    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        initViews(attrs)
    }

    constructor(
        context: Context, attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        initViews(attrs)

    }

    private fun initViews(nothing: AttributeSet?) {
        View.inflate(context, R.layout.global_offer_view_ab, this)
        tv_global_offer_msg.loadUrl("https://placeholder.com/")
        Log.d("LKB!", "" + childCount)
        getChildAt(0).isClickable = false;
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> return true
            MotionEvent.ACTION_UP -> {
                performClick()
                return true
            }
        }
        return false
    }

    // Because we call this from onTouchEvent, this code will be executed for both
    // normal touch events and for when the system calls this using Accessibility
    override fun performClick(): Boolean {
        super.performClick()
        doSomething()
        return true
    }

    private fun doSomething() {
        Toast.makeText(context, "did something", Toast.LENGTH_SHORT).show()
    }
}