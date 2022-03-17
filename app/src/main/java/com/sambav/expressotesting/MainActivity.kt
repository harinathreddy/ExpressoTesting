package com.sambav.expressotesting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    var editText: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.inputField);
    }
    fun onClick( view : View)
    {
        //Toast.makeText(applicationContext,"asdasd",Toast.LENGTH_LONG).show()
        editText?.setText("Lalala")
        val intent = Intent(this,MainActivity3::class.java)
        intent.putExtra("txt","hari")
        startActivity(intent)
    }

}