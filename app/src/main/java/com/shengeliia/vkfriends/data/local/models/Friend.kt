package com.shengeliia.vkfriends.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friends")
data class Friend (
    @PrimaryKey val userId: String,
    val firstName: String,
    val lastName: String,
    val photoUrl: String,
    val city: String?,
    var isOnline: Boolean
)