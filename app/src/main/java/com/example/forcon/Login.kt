package com.example.forcon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.forcon.databinding.ActivityAddBinding.inflate
import com.example.forcon.databinding.ActivityLoginBinding
import com.example.forcon.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    //lateinit var button: Button
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtSignup.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnSubmit2.setOnClickListener {
            val email = binding.eEmail.text.toString()
            val pass = binding.editTextTextPassword2.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {

                        val intent = Intent(this, Add::class.java)
                        startActivity(intent)

                    }else{
                        Toast.makeText(this, it.exception.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this,"Empty fields are not allowed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}