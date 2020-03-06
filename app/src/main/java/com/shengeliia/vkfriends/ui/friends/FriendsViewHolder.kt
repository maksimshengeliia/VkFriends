package com.shengeliia.vkfriends.ui.friends

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shengeliia.vkfriends.R

class FriendsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val photoView: ImageView = view.findViewById(R.id.itemPhoto)
    val name: TextView = view.findViewById(R.id.itemName)
    val city: TextView = view.findViewById(R.id.itemCity)
    val indicator: View = view.findViewById(R.id.itemIndicator)
}