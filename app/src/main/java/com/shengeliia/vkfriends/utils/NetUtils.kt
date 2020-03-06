package com.shengeliia.vkfriends.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import com.shengeliia.vkfriends.App


fun getParamsFromUrl(string: String): Map<String, String> {
    val map = mutableMapOf<String, String>()
    val key = StringBuilder()
    val value = StringBuilder()
    var isKey = true
    string.forEach {
        when (it) {
            '&' -> {
                map[key.toString()] = value.toString()
                key.clear()
                isKey = true
            }
            '=' -> {
                value.clear()
                isKey = false
            }
            else -> {
                if (isKey) {
                    key.append(it)
                } else {
                    value.append(it)
                }
            }
        }
    }
    if (key.isNotEmpty() && value.isNotEmpty()) {
        map[key.toString()] = value.toString()
    }
    return map
}

fun checkInternetConnection(): Boolean {
    val connManager =
        App.appInstance!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    var isConnected = false
    if (connManager != null) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            connManager.getNetworkCapabilities(connManager.activeNetwork)?.let {
                isConnected = it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||  it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
            }
        } else {
            val activeNetwork: NetworkInfo? = connManager.activeNetworkInfo
            isConnected = activeNetwork?.isConnectedOrConnecting == true
        }
    }

    return isConnected
}