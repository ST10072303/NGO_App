
package com.example.forcon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var button:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button=findViewById(R.id.btnSignin)

        button.setOnClickListener {
            val intent=Intent(this,Signup::class.java)
            startActivity(intent)
        }


        // Database connection

        val database = Firebase.database
        val myRef = database.getReference("Age")

        myRef.setValue("23")
    }
}