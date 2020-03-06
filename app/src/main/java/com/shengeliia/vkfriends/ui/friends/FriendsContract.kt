package com.shengeliia.vkfriends.ui.friends

import com.shengeliia.vkfriends.data.local.models.Friend
import com.shengeliia.vkfriends.data.local.models.UserInfo
import com.shengeliia.vkfriends.ui.BasePresenter

interface FriendsContract {
    interface PresenterMVP: BasePresenter<ViewMVP> {
        fun onRefreshFriends()
        fun onLogout()
    }
    interface ViewMVP {
        fun logout()
        fun showFriends(friends: List<Friend>)
        fun showLoading()
        fun showToolbarInfo(info: UserInfo)
        fun dismissLoading()
        fun showErrorMessage(text: String)
    }
}