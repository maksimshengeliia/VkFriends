package com.shengeliia.vkfriends.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shengeliia.vkfriends.data.local.models.Friend

@Database(entities = [Friend::class], version = 1)
abstract class FriendsDatabase : RoomDatabase() {
    abstract fun friendsDao(): FriendsDao
}