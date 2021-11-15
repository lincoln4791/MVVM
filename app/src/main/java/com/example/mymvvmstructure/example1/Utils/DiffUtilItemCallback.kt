package com.example.mymvvmstructure.example1.Utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class DiffUtilItemCallback <T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem==newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem==newItem
    }
}