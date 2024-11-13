package com.example.forcon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.forcon.databinding.ActivityReferralBinding
import com.google.firebase.database.FirebaseDatabase

class Referral : AppCompatActivity() {
    private lateinit var binding: ActivityReferralBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReferralBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnpadd.setOnClickListener {
            val intent = Intent(this, Add::class.java)
            startActivity(intent)
        }
        binding.btnpinf.setOnClickListener {
            val intent = Intent(this, PatientInfo::class.java)
            startActivity(intent)
        }

        binding.btnBook.setOnClickListener {

            val fullNames = binding.edFullNames.text.toString()
            val contact = binding.pNumbook.text.toString()
            val medicalCondition = binding.pBookreason.text.toString()
            val date = binding.PBookdate.text.toString()

            //initialise firebase database database reference
            var database = FirebaseDatabase.getInstance().getReference("Referrals")

            //create a Patient object
            val referral =
                Referrals(fullNames, contact, medicalCondition, date)

            database.child(fullNames).setValue(referral).addOnSuccessListener {
                //clear fields after saving
                binding.edFullNames.text.clear()
                binding.pNumbook.text.clear()
                binding.pBookreason.text.clear()
                binding.PBookdate.text.clear()
                //show success message
                Toast.makeText(this, "Successfully saved", Toast.LENGTH_SHORT).show()

            }   //show failure message
                .addOnFailureListener {
                    Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
                }
        }
    }
}
