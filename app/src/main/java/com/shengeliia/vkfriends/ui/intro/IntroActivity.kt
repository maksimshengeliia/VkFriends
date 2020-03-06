package com.shengeliia.vkfriends.ui.intro

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.shengeliia.vkfriends.R
import com.shengeliia.vkfriends.ui.auth.AuthActivity
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class IntroActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    private lateinit var loginButton: Button
    private lateinit var viewPager: ViewPager
    private lateinit var indicator: WormDotsIndicator
    private val pages: MutableList<View> = mutableListOf()
    private val numOfPages = 3
    private val colors: List<Long> = listOf(0xFF6200EE, 0xFF00897B, 0xFFFB8C00)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        findViews()
        initPages()

        val adapter = IntroAdapter(pages)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(this)
        indicator.setViewPager(viewPager)

        loginButton.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
    }

    private fun findViews() {
        indicator = findViewById(R.id.introIndicator)
        viewPager = findViewById(R.id.introViewPager)
        loginButton = findViewById(R.id.introLoginButton)
    }

    private fun initPages() {
        for (i in 0 until numOfPages) {
            val page = layoutInflater.inflate(R.layout.intro_layout, null)
            setPageResources(page, i)
            pages.add(page)
        }
    }

    private fun setPageResources(view: View, num: Int) {
        val image = view.findViewById<ImageView>(R.id.introPagerImage)
        val title = view.findViewById<TextView>(R.id.introPagerTitle)
        title.setTextColor(colors[num].toInt())
        when(num) {
            0 -> {
                image.setImageResource(R.drawable.ic_runner)
                title.text = resources.getString(R.string.page_fast)
            }
            1 -> {
                image.setImageResource(R.drawable.ic_clown)
                title.text = resources.getString(R.string.page_fun)
            }
            else -> {
                image.setImageResource(R.drawable.ic_trust)
                title.text = resources.getString(R.string.page_trust)
            }
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        val color = colors[position].toInt()
        loginButton.background = ColorDrawable(color)
        indicator.setDotIndicatorColor(color)
    }
}
