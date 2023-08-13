package com.example.task.view.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.task.R
import com.example.task.databinding.ProfileScreenFragBinding
import com.example.task.others.Constants
import com.example.task.view.activities.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private lateinit var binding: ProfileScreenFragBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileScreenFragBinding.inflate(inflater, container, false)

        sharedPreferences = requireContext().getSharedPreferences(
            Constants.PREFERENCE,
            Context.MODE_PRIVATE
        )

        val name = sharedPreferences.getString(Constants.NAME, "")
        val mobile = sharedPreferences.getString(Constants.MOBILE, "")
        val email = sharedPreferences.getString(Constants.EMAIL, "")
        val state = sharedPreferences.getString(Constants.STATE, "")
        val city = sharedPreferences.getString(Constants.CITY, "")
        val gender = sharedPreferences.getString(Constants.GENDER, "")

        ///Gender condition
        if (gender.equals("Male")) {
            binding.personImg.setImageResource(R.drawable.maleimg)
        } else {
            binding.personImg.setImageResource(R.drawable.femaleimg)
        }

        binding.nameTxt.text = name
        binding.mobileTxt.text = mobile
        binding.emailTxt.text = email
        binding.stateTxt.text = state
        binding.cityTxt.text = city

        ///Logout onclick button
        binding.logoutLout.setOnClickListener {
            alertLogoutDialog()
        }

        return binding.root
    }

    fun alertLogoutDialog() {
        val builder = AlertDialog.Builder(requireContext())
        // set the custom layout
        val customLayout: View = layoutInflater.inflate(R.layout.logout_laout, null)
        builder.setView(customLayout)
        val noText = customLayout.findViewById<TextView>(R.id.noTxt)
        val yesText = customLayout.findViewById<TextView>(R.id.yesTxt)

        // create and show the alert dialog
        val dialog = builder.create()
        ///no onclick
        noText.setOnClickListener {
            dialog.cancel()
        }

        ///yes onclick
        yesText.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        dialog.show()
    }

}