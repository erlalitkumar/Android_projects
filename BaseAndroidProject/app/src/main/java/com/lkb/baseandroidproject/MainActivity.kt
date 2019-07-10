package com.lkb.baseandroidproject

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            showAddItemDialog(this)
        }
    }
    private fun showAddItemDialog(c: Context) {
        val dialog = Dialog(c)
        dialog.setContentView(R.layout.custom)
        val dialogButton = dialog.findViewById(R.id.dialogButtonOK) as Button
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(applicationContext, "Dismissed..!!", Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }
}
