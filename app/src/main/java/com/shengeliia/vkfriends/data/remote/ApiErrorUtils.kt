package com.shengeliia.vkfriends.data.remote

import com.shengeliia.vkfriends.App
import com.shengeliia.vkfriends.R
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

data class ApiError(val code: Int, val message: String)

class ApiErrorException(val apiError: ApiError) : Exception()

fun parseError(response: Response<*>): ApiError {
    return try {
        val jsonObject = JSONObject(response.message())
        val code = jsonObject.getInt("code")
        val message = jsonObject.getString("message")
        ApiError(code, message)
    } catch (e: JSONException) {
        ApiError(0, App.appInstance!!.getString(R.string.default_error_message))
    }
}