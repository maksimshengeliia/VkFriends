package com.shengeliia.vkfriends.ui.intro

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class IntroAdapter(private val list: List<View>) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        Log.d("superc", "destroyItem $position")
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        Log.d("superc", "instantiateItem $position")
        val view = list[position]
        container.addView(view)
        return view
    }

    override fun getCount(): Int = list.size
}