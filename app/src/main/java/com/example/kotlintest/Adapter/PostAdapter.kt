package com.example.kotlintest.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintest.CompanyInfoActivity
import com.example.kotlintest.Model.CompanyItem
import com.example.kotlintest.R
import com.squareup.picasso.Picasso


class PostAdapter(internal var context: Context, internal var postList:List<CompanyItem>):RecyclerView.Adapter<PostViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_layout, parent, false)
        return PostViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.id.text = "id " + postList[position].id.toString()
        holder.name.text = postList[position].name
        Picasso.with(context).load("http://megakohz.bget.ru/test_task/" + postList[position].img)
            .placeholder(R.mipmap.ic_launcher_round)// optional
            .error(R.mipmap.ic_launcher)// optional
            .into(holder.image);
        holder.itemView.setOnClickListener {
            val intent = Intent(context, CompanyInfoActivity::class.java)
            intent.putExtra("id", postList[position].id.toString())
            context.startActivity(intent)
        }
    }

}