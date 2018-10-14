package com.albertjsoft.githubkanban.ui.main.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.albertjsoft.githubkanban.model.Repository
import com.albertjsoft.githubkanban.ui.main.FakeKanbanFragment
import com.albertjsoft.githubkanban.ui.main.RepoFragment

/**
 * Created by albertj on 15/10/2018.
 */
class TabsAdapter(fm: FragmentManager, repos: List<Repository>) : FragmentPagerAdapter(fm) {

    private val mTabTitles = arrayOf("Explore", "Local")
    private val mRepos = repos

    override fun getCount(): Int {
        return TAB_COUNT
    }

    override fun getItem(i: Int): Fragment {
        if(i == 0) {
            return RepoFragment.newInstance(mRepos)
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
