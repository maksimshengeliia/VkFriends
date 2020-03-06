package com.shengeliia.vkfriends.ui.friends

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.shengeliia.vkfriends.R
import com.shengeliia.vkfriends.data.local.Friend
import com.shengeliia.vkfriends.ui.intro.IntroActivity

class FriendsActivity : AppCompatActivity(), FriendsContract.ViewMVP, SwipeRefreshLayout.OnRefreshListener {
    private val presenter: FriendsContract.PresenterMVP = FriendsPresenter()

    private lateinit var toolbar: Toolbar
    private lateinit var friendsList: RecyclerView
    private lateinit var message: TextView
    private lateinit var refresh: SwipeRefreshLayout
    private val adapter = FriendsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        findViews()

        setSupportActionBar(toolbar)
        refresh.setOnRefreshListener(this)
        friendsList.adapter = adapter
        friendsList.layoutManager = LinearLayoutManager(this)
    }

    private fun findViews() {
        toolbar = findViewById(R.id.friendsToolbar)
        friendsList = findViewById(R.id.friendsList)
        message = findViewById(R.id.friendsMessage)
        refresh = findViewById(R.id.friendsRefresh)
    }

    override fun onStart() {
        super.onStart()
        presenter.register(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unregister()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.action_logout -> {
            presenter.onLogout()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun logout() {
        val intent = Intent(this, IntroActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showFriends(friends: List<Friend>) {
        friendsList.visibility = View.VISIBLE
        message.visibility = View.GONE
        adapter.friendsList = friends
    }

    override fun showErrorMessage(text: String) {
        friendsList.visibility = View.GONE
        message.visibility = View.VISIBLE
        message.text = text
    }

    override fun showLoading() {
        refresh.isRefreshing = true
    }

    override fun dismissLoading() {
        refresh.isRefreshing = false
    }

    override fun onRefresh() {
        presenter.onRefreshFriends()
    }
}
