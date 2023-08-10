package com.example.task.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.task.R
import com.example.task.databinding.ActivityRegisterBinding
import com.example.task.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private var databaseReference: DatabaseReference? = null
    private var firebaseDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null
    var generTxt: String? = "Male"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase!!.getReference("user")

        binding.regBtn.setOnClickListener {
            addData()
        }

        ///Male onclick
        binding.maleTxt.setOnClickListener {
            generTxt = "Male"
            binding.maleTxt.setTextColor(ContextCompat.getColor(this, R.color.white))
            binding.femaleTxt.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.maleTxt.setBackgroundColor(ContextCompat.getColor(this, R.color.yellow))
            binding.femaleTxt.setBackgroundColor(0)
        }

        ///Female onclick
        binding.femaleTxt.setOnClickListener {
            generTxt = "Female"
            binding.femaleTxt.setTextColor(ContextCompat.getColor(this, R.color.white))
            binding.maleTxt.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.femaleTxt.setBackgroundColor(ContextCompat.getColor(this, R.color.yellow))
            binding.maleTxt.setBackgroundColor(0)
        }

    }

    fun addData() {
        val name = binding.nameEdit.text.toString()
        val mobile = binding.mobileNumberEdit.text.toString()
        val email = binding.emailEdit.text.toString()
        val state = binding.stateEdit.text.toString()
        val city = binding.cityEdit.text.toString()
        val password = binding.passwordEdit.text.toString()

        mAuth?.createUserWithEmailAndPassword(email, password)?.addOnCompleteListener(this) {
            if (it.isSuccessful) {
                val userData = User(name, mobile, email, generTxt, state, city, password)
                FirebaseDatabase.getInstance().getReference("user")
                    .child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(userData)
                    .addOnCompleteListener {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Successful Registered",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
            } else {
                Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}