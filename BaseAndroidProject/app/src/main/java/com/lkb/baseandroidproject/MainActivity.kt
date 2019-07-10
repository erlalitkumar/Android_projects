package com.lkb.baseandroidproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.DialogInterface
import android.widget.EditText
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            showAddItemDialog(this)
        }
    }
    private fun showAddItemDialog(c: Context) {
        val taskEditText = EditText(c)
        val dialog = AlertDialog.Builder(c)
            .setTitle("Add a new task")
            .setMessage("What do you want to do next?")
            .setView(taskEditText)
            .setPositiveButton("Add",
                DialogInterface.OnClickListener { dialog, which -> val task = taskEditText.text.toString() })
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }
}
