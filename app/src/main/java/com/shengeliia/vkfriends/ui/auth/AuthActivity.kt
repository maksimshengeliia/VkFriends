package com.shengeliia.vkfriends.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.shengeliia.vkfriends.R
import com.shengeliia.vkfriends.ui.friends.FriendsActivity
import com.shengeliia.vkfriends.utils.checkInternetConnection

class AuthActivity : AppCompatActivity(), AuthContract.ViewMVP, AuthContract.WebStatesListener {

    private val presenter: AuthContract.PresenterMVP = AuthPresenter()

    companion object {
        const val CLIENT_ID = "7344124"
        const val REDIRECT_URL = "https://oauth.vk.com/blank.html"
        const val SCOPES = "friends,offline"
        const val VK_VERSION = "5.103"
    }

    private val authUrl = "https://oauth.vk.com/authorize?" +
            "client_id=$CLIENT_ID&" +
            "display=mobile&" +
            "redirect_uri=$REDIRECT_URL&" +
            "scope=$SCOPES&" +
            "response_type=token&" +
            "v=$VK_VERSION&" +
            "revoke=1"

    private lateinit var webView: WebView
    private lateinit var toolbar: Toolbar
    private lateinit var progress: ProgressBar
    private lateinit var errorMessage: TextView

    private lateinit var client: AuthWebClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        findViews()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
        super.onStart()
        presenter.register(this)

        client = AuthWebClient(this)
        webView.webViewClient = client
        if (checkInternetConnection()) {
            webView.loadUrl(authUrl)
        } else {
            onNetworkFailed()
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.unregister()
        client.recycler()
    }

    private fun findViews() {
        webView = findViewById(R.id.authWebView)
        toolbar = findViewById(R.id.authToolbar)
        progress = findViewById(R.id.authProgressBar)
        errorMessage = findViewById(R.id.authErrorMessage)
    }

    override fun onResponse(token: String?, clientId: String?) {
        presenter.onTokenCome(token, clientId)
    }

    override fun onStartLoading() {
        showLoading()
    }

    override fun onFinishLoading() {
        showWebPage()
    }

    override fun onNetworkFailed() {
        showErrorMessage(getString(R.string.internet_error_message))
    }

    override fun showLoading() {
        webView.visibility = View.INVISIBLE
        errorMessage.visibility = View.GONE
        progress.visibility = View.VISIBLE
    }

    override fun showWebPage() {
        webView.visibility = View.VISIBLE
        errorMessage.visibility = View.GONE
        progress.visibility = View.GONE
    }

    override fun showErrorMessage(msg: String) {
        webView.visibility = View.INVISIBLE
        errorMessage.visibility = View.VISIBLE
        errorMessage.text = msg
        progress.visibility = View.GONE
    }

    override fun showSuccessLogin() {
        val intent = Intent(this, FriendsActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun goBack() {
        finish()
    }
}
