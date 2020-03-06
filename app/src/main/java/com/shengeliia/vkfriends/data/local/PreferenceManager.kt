package com.shengeliia.vkfriends.data.local

import android.content.Context
import com.shengeliia.vkfriends.App

object PreferenceManager {
    private const val PREFERENCE_FILE_NAME = "vk_friends"
    private const val APP_TOKEN = "user_token"

    fun getUserToken(): String {
        val sp = App.appInstance!!.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
        return sp.getString(APP_TOKEN, "")!!
    }

    fun setUserToken(token: String) {
        val sp = App.appInstance!!.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
        sp.edit()
            .putString(APP_TOKEN, token)
            .apply()
    }

    fun deleteUserToken() {
        val sp = App.appInstance!!.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
        sp.edit()
            .remove(APP_TOKEN)
            .apply()
    }

    fun isUserTokenSet(): Boolean {
        val sp = App.appInstance!!.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
        return sp.contains(APP_TOKEN)
    }

}