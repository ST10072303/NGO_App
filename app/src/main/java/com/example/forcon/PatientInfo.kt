package com.example.forcon
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.forcon.databinding.ActivityPatientInfoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PatientInfo : AppCompatActivity() {

    private lateinit var binding: ActivityPatientInfoBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnpadd.setOnClickListener {
            val intent = Intent(this, Add::class.java)
            startActivity(intent)
        }
        binding.btnPBook.setOnClickListener {
            val intent = Intent(this, Referral::class.java)
            startActivity(intent)
        }

        binding.btnSearch.setOnClickListener {

            val patientid = binding.tvPatientID.text.toString()
            if(patientid.isNotEmpty()){

                readData(patientid)

            } else{
                Toast.makeText(this,"Enter Patient Name",Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun readData(patientid: String)
        {
        database = FirebaseDatabase.getInstance().getReference("Patients")

        database.child(patientid).get().addOnSuccessListener {
            if(it.exists()){

                val fullnames = it.child("fullNames").value
                val Gender = it.child("gender").value
                val Address = it.child("address").value
                val Age = it.child("age").value
                val ContactNumber = it.child("contactNumber").value
                val MedicalCondition = it.child("medicalCondition").value

                Toast.makeText(this,"Results found",Toast.LENGTH_SHORT).show()

                binding.tvPatientID.text.clear()

                binding.tvFullName.text = fullnames.toString()
                binding.tvGender.text = Gender.toString()
                binding.tvAddress.text = Address.toString()
                binding.tvAge.text = Age.toString()
                binding.tvContactNumber.text = ContactNumber.toString()
                binding.tvMedicalCondition.text = MedicalCondition.toString()
            }
            else{
                Toast.makeText(this,"Patient name does not exist",Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener{
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
        }
    }
}


