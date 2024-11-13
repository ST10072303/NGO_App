package com.example.forcon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Signup : AppCompatActivity() {

    lateinit var button:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        button=findViewById(R.id.BtLogin)

        button.setOnClickListener {
            val intent= Intent(this,Signin::class.java)
            startActivity(intent)
        }
    }
}