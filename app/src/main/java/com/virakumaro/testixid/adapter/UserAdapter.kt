package com.virakumaro.testixid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.virakumaro.testixid.R
import com.virakumaro.testixid.model.User


internal class UsersAdapter(private var items: ArrayList<User> = arrayListOf()) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = items[position]
        item.let {
            holder.textViewName.text = it.login
            Glide.with(holder.itemView)
                .load(it.avatar_url)
            holder.textDesc.text = it.html_url
            holder.itemView.setOnClickListener { view ->
                Toast.makeText(holder.itemView.context, "${it.login} \n ${it.html_url}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    fun addItems(githubUserList: List<User>) {
        items.addAll(githubUserList)
        notifyDataSetChanged()
    }

    fun clearData() {
        items.clear()
    }

    internal class UserViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val textDesc: TextView
        val textViewName: TextView
        val imageViewAvatar: ImageView

        init {
            imageViewAvatar = v.findViewById(R.id.imageview_userprofilepic) as ImageView
            textViewName = v.findViewById(R.id.textview_username)
            textDesc = v.findViewById(R.id.textview_user_profile_info)
        }
    }
}