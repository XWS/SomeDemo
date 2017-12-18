package com.example.shiji_xc.headandfootrecycleview

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    fun initView(){
        val btn = findViewById<Button>(R.id.btn1)
        btn.setOnClickListener {
            val intent=Intent()
            intent.setClass(this,RecycleViewActivity::class.java)
            startActivity(intent)
        }
    }
}
