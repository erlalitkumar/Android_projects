package com.lkb.baseandroidproject

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.qrcode_main.*

class ChildQRCodeActivity:FragmentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qrcode_main)
        var text="Hello" // Whatever you need to encode in the QR code
        var multiFormatWriter = MultiFormatWriter();
        try {
            var bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,200,200);
            var barcodeEncoder = BarcodeEncoder();
            var bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);
        } catch (e: WriterException) {
            e.printStackTrace();
        }
    }
}