package com.example.task.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.coroutineScope
import com.example.task.R
import com.example.task.databinding.ActivityLoginBinding
import com.example.task.utils.displayToast
import com.example.task.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycle.coroutineScope.launchWhenCreated {
            authViewModel.user.collect {
                if (it.isLoading) {
                    binding.progress.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.progress.visibility = View.GONE
                    this@LoginActivity.displayToast(it.error)
                }
                it.data?.let {
                    binding.progress.visibility = View.GONE
                    startActivity(Intent(this@LoginActivity, NavigationActivity::class.java))
                    finishAffinity()
                }
            }
        }

        ///Login button click
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
        if (email.isNotEmpty()) {
            if (password.isNotEmpty()) {
                authViewModel.login(email, password)
            } else {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()
        }
    }

}