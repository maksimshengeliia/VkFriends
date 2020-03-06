package com.shengeliia.vkfriends.ui.auth

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import com.shengeliia.vkfriends.utils.checkInternetConnection
import com.shengeliia.vkfriends.utils.getParamsFromUrl

class AuthWebClient(private var webStateListener: AuthContract.WebStatesListener?) : WebViewClient() {
    private var shouldShowPage = true

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (!checkInternetConnection()) {
            webStateListener?.onNetworkFailed()
            return true
        }
        if (validateUrl(url)) {
            shouldShowPage = false
            return true
        }
        return false
    }

    private fun validateUrl(url: String?): Boolean {
        if (url != null && url.startsWith(AuthActivity.REDIRECT_URL)) {
            val map = getParamsFromUrl(url.substringAfter("#"))
            webStateListener?.onResponse(map["access_token"], map["user_id"])
            return true
        }
        return false
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        webStateListener?.onStartLoading()
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        if (shouldShowPage) webStateListener?.onFinishLoading()
    }

    fun recycler() {
        webStateListener = null
    }
}