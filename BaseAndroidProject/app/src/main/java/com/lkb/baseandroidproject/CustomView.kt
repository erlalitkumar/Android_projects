package com.lkb.baseandroidproject

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View

class CustomView : View {
    constructor(context: Context) : super(context) {
        initAttr(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttr(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttr(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        initAttr(attrs)
    }

    fun initAttr(attrs: AttributeSet?) {

    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawColor(Color.RED)
    }

}
