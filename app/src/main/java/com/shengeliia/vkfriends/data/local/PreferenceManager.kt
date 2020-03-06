package com.shengeliia.vkfriends.data.local

import android.content.Context
import com.shengeliia.vkfriends.App
import com.shengeliia.vkfriends.data.local.models.UserInfo

object PreferenceManager {
    private const val PREFERENCE_FILE_NAME = "vk_friends"
    private const val USER_TOKEN = "user_token"
    private const val USER_NAME = "user_name"
    private const val USER_PHOTO_URL = "user_photo_url"

    fun isUserTokenSet(): Boolean {
        val sp = App.appInstance!!.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
        return sp.contains(USER_TOKEN)
    }

    fun cleanUserData() {
        val sp = App.appInstance!!.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
        sp.edit()
            .remove(USER_TOKEN)
            .remove(USER_NAME)
            .remove(USER_PHOTO_URL)
            .apply()
    }

    fun saveUserData(info: UserInfo) {
        val sp = App.appInstance!!.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
        sp.edit()
            .putString(USER_TOKEN, info.userToken)
            .putString(USER_NAME, info.userName)
            .putString(USER_PHOTO_URL, info.userPhotoUrl)
            .apply()
    }

    fun getUserData(): UserInfo {
        val sp = App.appInstance!!.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
        val token = sp.getString(USER_TOKEN, "")!!
        val name = sp.getString(USER_NAME, "")!!
        val url = sp.getString(USER_PHOTO_URL, "")!!
        return UserInfo(token, name, url)
    }
}