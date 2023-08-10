package com.example.androidskilltask.views.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task.R
import com.example.task.model.User
import com.example.task.view.activities.UserDetailsActivity

class HomeListAdapter(
    val activity: Activity,
    val homeList: ArrayList<User>
) :
    RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_list, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val result = homeList[position]
        holder.nameTxt.setText(result.name)
        holder.itemView.rootView.setOnClickListener {
            val intent = Intent(activity, UserDetailsActivity::class.java)
            intent.putExtra("userresponse", homeList.get(position))
            activity.startActivity(intent)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return homeList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val nameTxt: TextView = itemView.findViewById(R.id.nameTxt)
    }

}