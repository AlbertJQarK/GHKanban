package com.albertjsoft.githubkanban.ui.main

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import com.albertjsoft.githubkanban.R

/**
 * Created by albertj on 13/10/2018.
 */
class MainActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    private var mIsAvatarShown = true
    private var mProfilePhoto: ImageView? = null
    private var mMaxScrollSize: Int = 0
    private var mPresenterMain: PresenterMain? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout = findViewById(R.id.tabs) as TabLayout
        val viewPager = findViewById(R.id.viewpager) as ViewPager
        val appbarLayout = findViewById(R.id.appbar) as AppBarLayout
        mProfilePhoto = findViewById(R.id.profile_photo) as ImageView

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        toolbar.setNavigationOnClickListener { onBackPressed() }

        appbarLayout.addOnOffsetChangedListener(this)
        mMaxScrollSize = appbarLayout.totalScrollRange

        viewPager.adapter = TabsAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)

        initializePresenter()
        mPresenterMain?.getUserData(intent.getStringExtra("username"))
    }

    private fun initializePresenter() {
        mPresenterMain = PresenterMain(this)

    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, i: Int) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.totalScrollRange

        val percentage = Math.abs(i) * 100 / mMaxScrollSize

        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false

            mProfilePhoto!!.animate()
                    .scaleY(0f).scaleX(0f)
                    .setDuration(200)
                    .start()
        }

        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true

            mProfilePhoto!!.animate()
                    .scaleY(1f).scaleX(1f)
                    .start()
        }
    }

    private class TabsAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        private val mTabTitles = arrayOf("Explore", "Local")

        override fun getCount(): Int {
            return TAB_COUNT
        }

        override fun getItem(i: Int): Fragment {
            if(i == 0) {
                return FakeRepoFragment.newInstance()
            }
            else {
                return FakeKanbanFragment.newInstance()
            }
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mTabTitles[position]
        }

        companion object {
            private val TAB_COUNT = 2
        }
    }

    companion object {

        private val PERCENTAGE_TO_ANIMATE_AVATAR = 20

    }

}
