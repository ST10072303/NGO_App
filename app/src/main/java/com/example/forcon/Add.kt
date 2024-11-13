package com.example.forcon

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.forcon.databinding.ActivityAddBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Add : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPinfo.setOnClickListener {
            val intent = Intent(this, PatientInfo::class.java)
            startActivity(intent)
        }
        binding.btnPBook.setOnClickListener {
            val intent = Intent(this, Referral::class.java)
            startActivity(intent)
        }

        binding.txtOut.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnPsubmit.setOnClickListener {
            val patientID = binding.edPatientID.text.toString()
            val fullNames = binding.pFullnames.text.toString()
            val gender = binding.pGender.text.toString()
            val address = binding.pAddress.text.toString()
            val age = binding.pAge.text.toString()
            val contactNumber = binding.pContact.text.toString()
            val medicalInformation = binding.pCondition.text.toString()

            //initialise firebase database database reference
            var database = FirebaseDatabase.getInstance().getReference("Patients")

            //create a Patient object
            val patient =
                Patients(patientID, fullNames, gender, address, age, contactNumber, medicalInformation)

            database.child(patientID).setValue(patient).addOnSuccessListener {
                //clear fields after saving
                binding.edPatientID.text.clear()
                binding.pFullnames.text.clear()
                binding.pGender.text.clear()
                binding.pAddress.text.clear()
                binding.pAge.text.clear()
                binding.pContact.text.clear()
                binding.pCondition.text.clear()

                //show success message
                Toast.makeText(this, "Successfully saved", Toast.LENGTH_SHORT).show()

            }   //show failure message
                .addOnFailureListener {
                    Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
                }
        }
    }
}
