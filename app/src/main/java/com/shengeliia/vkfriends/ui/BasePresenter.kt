package com.shengeliia.vkfriends.ui

interface BasePresenter<T> {
    fun register(view: T)
    fun unregister()
}