package com.shengeliia.vkfriends.ui.friends

import com.shengeliia.vkfriends.App
import com.shengeliia.vkfriends.R
import com.shengeliia.vkfriends.data.FriendsRepository
import com.shengeliia.vkfriends.data.local.Friend
import com.shengeliia.vkfriends.data.local.PreferenceManager
import com.shengeliia.vkfriends.data.remote.ApiErrorException
import com.shengeliia.vkfriends.data.remote.RetrofitClient.RESPONSE_CODE_SESSION_FAILED
import kotlinx.coroutines.*

class FriendsPresenter : FriendsContract.PresenterMVP {
    private var view: FriendsContract.ViewMVP? = null
    private val repository = FriendsRepository()
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onRefreshFriends() {
        scope.launch {
            try {
                val token = PreferenceManager.getUserToken()
                val friends = repository.getRemoteFriends(token)
                showFriends(friends)
            } catch (e: ApiErrorException) {
                // Если вк акссес токен пользователя - недействителен, то выйти
                if (e.apiError.code == RESPONSE_CODE_SESSION_FAILED) {
                    onLogout()
                } else {
                    showError(e.apiError.message)
                }
            }
            catch (e: Exception) {
                showError(App.appInstance!!.getString(R.string.default_error_message))
            }
        }
    }

    override fun onLogout() {
        scope.launch {
            repository.deleteFriends()
            PreferenceManager.deleteUserToken()
            withContext(Dispatchers.Main) {
                view?.logout()
            }
        }
    }

    override fun register(view: FriendsContract.ViewMVP) {
        this.view = view
        if (checkUserIsActivated()) {
            getFriendsFromCache()
        } else {
           onLogout()
        }
    }

    private fun getFriendsFromCache() {
        view?.showLoading()
        scope.launch {
            try {
                var friends = repository.getLocalFriends()
                if (friends.isEmpty()) {
                    val token = PreferenceManager.getUserToken()
                    friends = repository.getRemoteFriends(token)
                }
                showFriends(friends)
            } catch (e: ApiErrorException) {
                // Если вк акссес токен пользователя - недействителен, то выйти
                if (e.apiError.code == RESPONSE_CODE_SESSION_FAILED) {
                    onLogout()
                } else {
                    showError(e.apiError.message)
                }
            }
            catch (e: Exception) {
                showError(App.appInstance!!.getString(R.string.default_error_message))
            }
        }
    }

    private suspend fun showFriends(friends: List<Friend>) = withContext(Dispatchers.Main) {
        view?.dismissLoading()
        view?.showFriends(friends)
    }

    private suspend fun showError(msg: String) = withContext(Dispatchers.Main) {
        view?.dismissLoading()
        view?.showErrorMessage(msg)
    }

    private fun checkUserIsActivated() = PreferenceManager.isUserTokenSet()

    override fun unregister() {
        if (view != null) {
            view = null
        }
        scope.cancel()
    }
}