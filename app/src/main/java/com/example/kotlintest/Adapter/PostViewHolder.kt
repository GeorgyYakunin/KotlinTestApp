package com.example.kotlintest.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_layout.view.*

class PostViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val name = itemView.item_name
    val id = itemView.item_id
    val image = itemView.item_image
}