package com.albertjsoft.githubkanban.ui.main

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import com.albertjsoft.githubkanban.R

/**
 * Created by albertj on 13/10/2018.
 */
class MainActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    private var mIsPhotoShown = true
    private var mProfilePhoto: ImageView? = null
    private var mMaxScrollSize: Int = 0
    private var mPresenterMain: PresenterMain? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appbarLayout = findViewById(R.id.appbar) as AppBarLayout
        mProfilePhoto = findViewById(R.id.profile_photo) as ImageView

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        toolbar.setNavigationOnClickListener { onBackPressed() }

        appbarLayout.addOnOffsetChangedListener(this)
        mMaxScrollSize = appbarLayout.totalScrollRange

        initializePresenter()
        mPresenterMain?.getUserData(intent.getStringExtra("username"))
        mPresenterMain?.getUserRepos(intent.getStringExtra("username"))
    }

    private fun initializePresenter() {
        mPresenterMain = PresenterMain(this)

    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, i: Int) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.totalScrollRange

        val percentage = Math.abs(i) * 100 / mMaxScrollSize

        if (percentage >= PERCENTAGE && mIsPhotoShown) {
            mIsPhotoShown = false

            mProfilePhoto!!.animate()
                    .scaleY(0f).scaleX(0f)
                    .setDuration(250)
                    .start()
        }

        if (percentage <= PERCENTAGE && !mIsPhotoShown) {
            mIsPhotoShown = true

            mProfilePhoto!!.animate()
                    .scaleY(1f).scaleX(1f)
                    .start()
        }
    }

    companion object {

        private val PERCENTAGE = 20

    }

}
