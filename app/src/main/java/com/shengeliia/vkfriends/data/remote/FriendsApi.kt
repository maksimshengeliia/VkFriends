package com.shengeliia.vkfriends.data.remote

import com.shengeliia.vkfriends.data.local.models.Friend
import com.shengeliia.vkfriends.data.local.models.UserInfo
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface FriendsApi {
    @POST(RetrofitClient.API_FRIENDS)
    @FormUrlEncoded
    fun friends(@FieldMap lastTestId: Map<String, String>): Call<List<Friend>>

    @POST(RetrofitClient.API_LOGIN)
    @FormUrlEncoded
    fun login(@FieldMap lastTestId: Map<String, String>): Call<UserInfo>
}