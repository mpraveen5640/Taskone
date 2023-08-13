package com.example.task.view.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidskilltask.views.adapters.HomeListAdapter
import com.example.task.databinding.HomeScreenFragBinding
import com.example.task.model.User
import com.example.task.others.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HomeFragment : Fragment() {

    private lateinit var binding: HomeScreenFragBinding

    private lateinit var databaseReference: DatabaseReference
    private lateinit var userList: ArrayList<User>
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeScreenFragBinding.inflate(inflater, container, false)

        sharedPreferences = requireContext().getSharedPreferences(
            Constants.PREFERENCE,
            Context.MODE_PRIVATE
        )

        binding.recyclerView.layoutManager =
            LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)

        userList = arrayListOf<User>()
        fetchData()

        return binding.root
    }

    fun fetchData() {
        binding.progress.visibility = View.VISIBLE
        databaseReference = FirebaseDatabase.getInstance().getReference("user")
        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapShot in snapshot.children) {
                        val user = userSnapShot.getValue(User::class.java)
                        userList.add(user!!)
                    }

                    for (i in 0 until userList.size) {
                        if (userList[i].email.equals(currentFirebaseUser?.email)) {
                            val editor: SharedPreferences.Editor = sharedPreferences.edit()
                            editor.putString(Constants.NAME, userList.get(i).name)
                            editor.putString(Constants.MOBILE, userList.get(i).mobile)
                            editor.putString(Constants.EMAIL, userList.get(i).email)
                            editor.putString(Constants.GENDER, userList.get(i).gener)
                            editor.putString(Constants.STATE, userList.get(i).state)
                            editor.putString(Constants.CITY, userList.get(i).city)
                            editor.apply()
                            editor.commit()
                        }
                    }

                    if (userList.size > 0) {
                        binding.nodataTxt.visibility = View.GONE
                        binding.progress.visibility = View.GONE
                        binding.recyclerView.adapter = HomeListAdapter(
                            activity!!, userList
                        )
                        binding.nameTxt.text = sharedPreferences.getString(Constants.NAME, "")
                        binding.usersCountTxt.text = "Users: " + userList.size.toString()
                    } else {
                        binding.nodataTxt.visibility = View.VISIBLE
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                binding.progress.visibility = View.GONE
            }

        })
    }

}