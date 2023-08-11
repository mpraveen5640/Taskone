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
import com.example.task.databinding.ActivityRegisterBinding
import com.example.task.model.User
import com.example.task.utils.displayToast
import com.example.task.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    var generTxt: String? = "Male"
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycle.coroutineScope.launchWhenCreated {
            authViewModel.user.collect {
                if (it.isLoading) {
                    binding.progress.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.progress.visibility = View.GONE
                    this@RegisterActivity.displayToast(it.error)
                }
                it.data?.let {
                    binding.progress.visibility = View.GONE
                    startActivity(Intent(this@RegisterActivity, NavigationActivity::class.java))
                }
            }
        }

        ///Regster button onclick
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

        if (name.isNotEmpty()) {
            if (mobile.isNotEmpty()) {
                if (state.isNotEmpty()) {
                    if (city.isNotEmpty()) {
                        if (password.isNotEmpty()) {
                            val userData = User(
                                name,
                                mobile,
                                email,
                                generTxt,
                                state,
                                city,
                                password
                            )
                            authViewModel.register(email, password, userData)
                        } else {
                            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "Please enter city", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Please enter state", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter mobile", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show()
        }
    }
}