package com.example.productscanner

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {

    lateinit var buttn : Button
    lateinit var scan : IntentIntegrator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttn = findViewById(R.id.scan)
        scan = IntentIntegrator(this)
        buttn.setOnClickListener(){scanQrCode()}
    }

    private fun scanQrCode() {
        scan.initiateScan()
    }

    private fun showProduct(b: () -> String){
        val lamdaCall = {a: String->val amazon = Intent(android.content.Intent.ACTION_VIEW);
            amazon.data = Uri.parse("https://www.amazon.in/s?k=${a}"); //string template
            startActivity(amazon)} // lamda function
        lamdaCall(b())
    }

    override fun onActivityResult(request: Int, result: Int, data: Intent?) {
        super.onActivityResult(request, result, data)
        val qr = IntentIntegrator.parseActivityResult(request, result, data)
        showProduct{ qr?.contents.toString()} // passing function as parameter
    }
}

