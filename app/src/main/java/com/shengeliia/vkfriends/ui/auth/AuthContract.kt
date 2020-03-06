package com.shengeliia.vkfriends.ui.auth

import com.shengeliia.vkfriends.ui.BasePresenter

interface AuthContract {
    interface PresenterMVP: BasePresenter<ViewMVP> {
        fun onTokenCome(token: String?, clientId: String?)
    }
    interface ViewMVP {
        fun showLoading()
        fun showWebPage()
        fun showErrorMessage(msg: String)
        fun showSuccessLogin()
        fun goBack()
    }
    interface WebStatesListener {
        fun onResponse(token: String?, clientId: String?)
        fun onStartLoading()
        fun onFinishLoading()
        fun onNetworkFailed()
    }
}