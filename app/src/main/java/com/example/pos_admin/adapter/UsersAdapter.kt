package com.example.pos_admin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pos_admin.R
import com.example.pos_admin.data.entity.User


class UsersAdapter(private val context: Context, private val listOfUsers: List<User>):
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
    class UserViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val userName: TextView = view.findViewById(R.id.user_name)
        val userRole: TextView = view.findViewById(R.id.user_role)
        val userCode: TextView = view.findViewById(R.id.user_code)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.user, parent, false)

        return UserViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = listOfUsers[position]
        holder.userName.text = user.name
        holder.userRole.text = user.role
        holder.userCode.text = user.code
    }

    override fun getItemCount() = listOfUsers.size

}