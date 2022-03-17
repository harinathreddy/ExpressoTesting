package com.sambav.expressotesting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val textView = findViewById<TextView>(R.id.textView)
        val intent= getIntent().extras?.getString("txt")
        textView.text = intent
    }
}