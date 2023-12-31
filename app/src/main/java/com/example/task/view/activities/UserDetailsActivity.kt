package com.example.task.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.task.R
import com.example.task.databinding.ActivityUserDetailsBinding
import com.example.task.model.User

class UserDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailsBinding
    lateinit var userDetails: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.yellow)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)

        userDetails = intent.getSerializableExtra("userresponse") as User

        setTitle(userDetails.name)
        if (userDetails.gener.equals("Male")) {
            binding.cardColorLoyout.setBackgroundTintList(
                ContextCompat.getColorStateList(
                    this,
                    R.color.green
                )
            )
            binding.generTxt.setText(getString(R.string.male))
            binding.generTxt.setTextColor(ContextCompat.getColor(this, R.color.green))
        } else {
            binding.cardColorLoyout.setBackgroundTintList(
                ContextCompat.getColorStateList(
                    this,
                    R.color.red
                )
            )
            binding.generTxt.setText(getString(R.string.female))
            binding.generTxt.setTextColor(ContextCompat.getColor(this, R.color.red))
        }

        binding.nameTxt.setText(userDetails.name)
        binding.mobileTxt.setText(userDetails.mobile)
        binding.emailTxt.setText(userDetails.email)
        binding.stateTxt.setText(userDetails.state)
        binding.cityTxt.setText(userDetails.city)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}