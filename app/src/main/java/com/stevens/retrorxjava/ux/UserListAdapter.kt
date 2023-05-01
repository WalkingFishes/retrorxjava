package com.stevens.retrorxjava.ux

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stevens.retrorxjava.databinding.UserRowBinding
import com.stevens.retrorxjava.network.User

class UserListAdapter : ListAdapter<User, UserViewHolder>(UserDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemBinding = UserRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position).let { data ->
            holder.apply {
                userId.text = data.userId.toString()
                userName.text = data.name
            }
        }
    }

}

class UserDiff : DiffUtil.ItemCallback<User>() {
    override fun areContentsTheSame(
        oldItem: User,
        newItem: User
    ): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(
        oldItem: User,
        newItem: User
    ): Boolean =
        oldItem == newItem
}

class UserViewHolder(itemBinding: UserRowBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    val userId: TextView = itemBinding.userId
    val userName: TextView = itemBinding.userName
}