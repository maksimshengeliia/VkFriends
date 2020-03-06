package com.shengeliia.vkfriends.ui.auth

import com.shengeliia.vkfriends.App
import com.shengeliia.vkfriends.R
import com.shengeliia.vkfriends.data.FriendsRepository
import com.shengeliia.vkfriends.data.local.PreferenceManager
import com.shengeliia.vkfriends.data.remote.ApiErrorException
import kotlinx.coroutines.*

class AuthPresenter : AuthContract.PresenterMVP {
    private var view: AuthContract.ViewMVP? = null
    private var repository = FriendsRepository()
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onTokenCome(token: String?, clientId: String?) {
        if (token == null || clientId == null) {
            view?.goBack()
            return
        }

        view?.showLoading()
        scope.launch {
            try {
                val loginResponse = repository.login(token, clientId)
                PreferenceManager.saveUserData(loginResponse)
                showSuccessLogin()
            } catch (e: ApiErrorException) {
                showErrorMessage(e.apiError.message)
            } catch (e: Exception) {
                showErrorMessage(App.appInstance!!.getString(R.string.default_error_message))
            }
        }
    }

    private suspend fun showSuccessLogin() = withContext(Dispatchers.Main) {
        view?.showSuccessLogin()
    }

    private suspend fun showErrorMessage(msg: String) = withContext(Dispatchers.Main) {
        view?.showErrorMessage(msg)
    }

    override fun register(view: AuthContract.ViewMVP) {
        this.view = view
    }

    override fun unregister() {
        if (view != null) {
            view = null
        }
        scope.cancel()
    }
}