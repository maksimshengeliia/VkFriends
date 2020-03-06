package com.shengeliia.vkfriends.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shengeliia.vkfriends.data.local.models.Friend

@Dao
interface FriendsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFriends(list: List<Friend>)

    @Query("select * from friends")
    fun getFriends(): List<Friend>

    @Query("delete from friends")
    fun clean()
}