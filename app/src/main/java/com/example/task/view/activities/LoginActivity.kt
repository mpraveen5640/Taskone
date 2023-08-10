package com.example.task.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.task.R
import com.example.task.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance()

        binding.logInBtn.setOnClickListener {
            logInetails()
        }

        ///To Register screen
        binding.newUserTxt.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    fun logInetails() {
        val email = binding.emailEdit.text.toString()
        val password = binding.passwordEdit.text.toString()
        if (email.length > 0) {
            if (password.length > 0) {
                mAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@LoginActivity, NavigationActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else
                        Toast.makeText(this, "Log In failed", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()
        }
    }

}