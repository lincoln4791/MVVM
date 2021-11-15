package com.example.mymvvmstructure.example1.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.mymvvmstructure.R
import com.example.mymvvmstructure.example1.Utils.DiffUtilItemCallback
import com.example.mymvvmstructure.example1.modelClasses.User

class PostsAdapter :RecyclerView.Adapter<PostsAdapter.Holder>() {
    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tvName_sample)
        val tvAge = itemView.findViewById<TextView>(R.id.tvAge_sample)
    }

    val differ = AsyncListDiffer(this, DiffUtilItemCallback<User>())

    private var onItemClickListener:((User)->Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.sample_posts,parent,false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = differ.currentList[position]


        bindView(data,holder)
    }

    private fun bindView(data: User?, holder: Holder) {
        holder.tvName.text = data?.name
        holder.tvAge.text = data?.age

        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(data!!) }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setOnItemClickListener(listener: (User)->Unit){
        onItemClickListener = listener
    }
}