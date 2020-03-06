package com.shengeliia.vkfriends.data

import com.shengeliia.vkfriends.App
import com.shengeliia.vkfriends.data.local.Friend
import com.shengeliia.vkfriends.data.remote.ApiErrorException
import com.shengeliia.vkfriends.data.remote.RetrofitClient
import com.shengeliia.vkfriends.data.remote.RetrofitClient.PARAM_COUNT
import com.shengeliia.vkfriends.data.remote.RetrofitClient.PARAM_SECRET_KEY
import com.shengeliia.vkfriends.data.remote.RetrofitClient.PARAM_USER_ID
import com.shengeliia.vkfriends.data.remote.RetrofitClient.PARAM_USER_TOKEN
import com.shengeliia.vkfriends.data.remote.RetrofitClient.PARAM_VK_TOKEN
import com.shengeliia.vkfriends.data.remote.RetrofitClient.VALUE_COUNT
import com.shengeliia.vkfriends.data.remote.RetrofitClient.VALUE_SECRET_KEY
import com.shengeliia.vkfriends.data.remote.parseError

class FriendsRepository {
    fun login(vkToken: String, userId: String): String {
        val api = RetrofitClient.getApi()
        val call = api.login (
            hashMapOf (
                PARAM_SECRET_KEY to VALUE_SECRET_KEY,
                PARAM_USER_ID to userId,
                PARAM_VK_TOKEN to vkToken
            )
        )
        val response = call.execute()
        if (response.isSuccessful) {
            return response.body().toString()
        } else {
            throw ApiErrorException(parseError(response))
        }
    }

    /**
     *  Получить друзей из кэша
    * */
    fun getLocalFriends(): List<Friend> {
        val dao = App.friendsDatabase!!.friendsDao()
        return dao.getFriends()
    }

     /**
     *  Обратиться к серверу, и получить друзей
     * */
    fun getRemoteFriends(appToken: String): List<Friend> {
         val api = RetrofitClient.getApi()
         val call = api.friends(
             hashMapOf (
                 PARAM_SECRET_KEY to VALUE_SECRET_KEY,
                 PARAM_COUNT to VALUE_COUNT,
                 PARAM_USER_TOKEN to appToken
             )
         )
         val response = call.execute()

         if (response.isSuccessful) {
             val friends = response.body()!!
             // Очистить кэш
             deleteFriends()
             // Закэшировать друзей в локальной базе данных
             insertFriends(friends)
             return friends
         } else {
             throw ApiErrorException(parseError(response))
         }
    }

    fun insertFriends(friends: List<Friend>) {
        val dao = App.friendsDatabase!!.friendsDao()
        dao.insertFriends(friends)
    }

    fun deleteFriends() {
        val dao = App.friendsDatabase!!.friendsDao()
        dao.clean()
    }
}