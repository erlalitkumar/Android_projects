package com.lkb.baseandroidproject

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var isClicked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var count =0
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                count += dy
                Toast.makeText(this@MainActivity, "$dx and $count", Toast.LENGTH_SHORT).show()
                if(dy>0){
                    inAndOutLinearAnimation(60f, 0f)
                }else{
                    inAndOutLinearAnimation(0f, 60f)
                }
            }

        })
        button.setOnClickListener {
            isClicked = !isClicked
            if (isClicked) {
                inAndOutLinearAnimation(60f, 0f)
            } else {
                inAndOutLinearAnimation(0f, 60f)
            }
        }
        recyclerView.apply {
            adapter = LocalAdapter(
                listOf(
                    "Apple",
                    "Orange",
                    "Banana",
                    "Pineapple",
                    "Cherry",
                    "wood Apple",
                    "naseberry",
                    "Apple",
                    "Orange",
                    "Banana",
                    "Pineapple",
                    "Cherry",
                    "wood Apple",
                    "naseberry"
                )
            )
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun inAndOutLinearAnimation(param1: Float, param2: Float) {
        val widthAnimator = ValueAnimator.ofFloat(param1, param2)
        widthAnimator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Float
            iconContainer.translationX = animatedValue
            Log.d("ANIM", " animated value = $animatedValue")
            widthAnimator.duration = 400
            if (!iconContainer.isInLayout) {
                iconContainer.requestLayout()
            }

        }
        widthAnimator.start()
    }
}
