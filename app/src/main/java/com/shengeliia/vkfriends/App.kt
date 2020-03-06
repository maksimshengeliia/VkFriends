package com.shengeliia.vkfriends

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.shengeliia.vkfriends.data.local.FriendsDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
        friendsDatabase = Room.databaseBuilder(this, FriendsDatabase::class.java, "friends_database")
            .build()
    }

    companion object {
        var appInstance: Context? = null
            private set
        var friendsDatabase: FriendsDatabase? = null
            private set
    }
}