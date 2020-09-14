package com.lkb.baseandroidproject

import android.os.Bundle
import android.webkit.WebChromeClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mimeType = "text/html"
        val encoding = "UTF-8"
        val html = loadData("index.html")
        //Enable Javascript
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = object : WebChromeClient() {
        }
        //Inject WebAppInterface methods into Web page by having Interface 'Android'
        webView.addJavascriptInterface(WebAppInterface(this),"Android")
        webView.loadDataWithBaseURL("", html, mimeType, encoding, "")
        button.setOnClickListener {
            webView.evaluateJavascript("testEcho()", null)
            //bellow kitkat
            //webView.loadUrl("javascript:testEcho();")
        }
    }

    fun loadData(inFile: String?): String? {
        var tContents: String? = ""
        try {
            val stream: InputStream = assets.open(inFile)
            val size: Int = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            tContents = String(buffer)
        } catch (e: IOException) {
            // Handle exceptions here
        }
        return tContents
    }

}
