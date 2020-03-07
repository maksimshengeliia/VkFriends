package com.shengeliia.vkfriends.data.remote

import com.google.gson.GsonBuilder
import com.shengeliia.vkfriends.data.remote.BuildVars.SERVER_SECRET_WORD
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    const val REMOTE_SERVER_URL = "https://maksimshengeliia.azurewebsites.net/vkfriends/"
    const val API_FRIENDS = "friends.php"
    const val API_LOGIN = "login.php"
    /**
    *   Код ошибки, приходящий с сервера, если вк токен пользователя - недействителен
    * */
    const val RESPONSE_CODE_SESSION_FAILED = 3
    const val PARAM_SECRET_KEY = "secretKey"
    const val VALUE_SECRET_KEY = SERVER_SECRET_WORD // Секретный ключ приложения
    const val PARAM_USER_ID = "userId"
    const val PARAM_VK_TOKEN = "vkToken"
    const val PARAM_USER_TOKEN = "userToken"
    const val PARAM_COUNT = "count"
    const val VALUE_COUNT = "5"

    fun getApi(): FriendsApi {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(REMOTE_SERVER_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(FriendsApi::class.java)
    }
}