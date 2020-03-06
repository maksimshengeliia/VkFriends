package com.shengeliia.vkfriends.ui.friends

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shengeliia.vkfriends.R
import com.shengeliia.vkfriends.data.local.models.Friend
import com.squareup.picasso.Picasso
import kotlin.random.Random


class FriendsAdapter: RecyclerView.Adapter<FriendsViewHolder>() {
    private var colors: List<Long> = listOf(0xFFE57373, 0xFFF06292, 0xFFBA68C8, 0xFF9575CD, 0xFF64B5F6)
    var friendsList: List<Friend> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_friend, parent, false)
        return FriendsViewHolder(view)
    }

    override fun getItemCount() = friendsList.size

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        val friend = friendsList[position]
        holder.name.text = "${friend.firstName} ${friend.lastName}"
        holder.city.text = friend.city ?: "Город неизвестен"
        holder.indicator.visibility = if(friend.isOnline) View.VISIBLE else View.GONE
        Picasso.get()
            .load(friend.photoUrl)
            .placeholder(ColorDrawable(colors[Random.nextInt(colors.size)].toInt()))
            .into(holder.photoView)
    }
}