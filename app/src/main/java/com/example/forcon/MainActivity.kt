package com.example.forcon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
//import com.google.firebase.database.ktx.database
//import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var button:Button
    lateinit var button2 :Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button=findViewById(R.id.btnRegs)


         button.setOnClickListener {
             val intent=Intent(this,Registration::class.java)
             startActivity(intent)
         }
        button2=findViewById(R.id.btnSignin)


        button2.setOnClickListener {
            val intent=Intent(this, Login::class.java)
            startActivity(intent)
        }
        // Database connection

        //val database = Firebase.database
    }
}