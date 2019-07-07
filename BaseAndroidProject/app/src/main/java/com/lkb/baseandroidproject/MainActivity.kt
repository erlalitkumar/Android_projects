package com.lkb.baseandroidproject

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val filename = "hello.txt"
        val fileContents = "Hello world!"
        writeFile(filename,fileContents)
        val content = readFile(filename)
        Log.d("File",content)
    }

    private fun writeFile(filename: String,fileContents:String) {
        this.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(fileContents.toByteArray())
            Log.d("File", "Success")
        }
    }

    private fun readFile(filename: String): String {
        var fileInputStream: FileInputStream? = null
        fileInputStream = openFileInput(filename)
        var inputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        val stringBuilder: StringBuilder = StringBuilder()
        var text: String? = null
        while ({ text = bufferedReader.readLine(); text }() != null) {
            stringBuilder.append(text)
        }
        return stringBuilder.toString()
    }
}
