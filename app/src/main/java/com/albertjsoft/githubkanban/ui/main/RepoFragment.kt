package com.albertjsoft.githubkanban.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.albertjsoft.githubkanban.R

import com.albertjsoft.githubkanban.model.Repository

/**
 * Created by albertj on 13/10/2018.
 */

class RepoFragment : BaseFragment(){

    private var mRootView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater!!.inflate(R.layout.fragment_page, container, false) as RecyclerView
        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mRootView!!.adapter = RepoAdapter()
    }

    class RepoAdapter internal constructor() : RecyclerView.Adapter<RepoVH>() {

        private var mRepostories: List<Repository>? = mRepos

        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RepoVH {
            val itemView = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.list_item, viewGroup, false)
            itemView.findViewById<Button>(R.id.action_button).setText(R.string.add)
            itemView.findViewById<TextView>(R.id.item_title).setText(R.string.repository)
            return RepoVH(itemView)
        }

        override fun onBindViewHolder(RepoVH: RepoVH, position: Int) {
            var repo : Repository = mRepostories!!.get(position)
            RepoVH.mRepoName?.text = repo.name
            RepoVH.mRepoOwner?.text = repo.user?.login

        }

        override fun getItemCount(): Int {
            return mRepos?.size as Int
        }

    }

    class RepoVH internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        var mRepoName : TextView? = null
        var mRepoOwner : TextView? = null

        init{
            mRepoName = itemView.findViewById(R.id.item_title) as TextView
            mRepoOwner = itemView.findViewById(R.id.item_message) as TextView
        }
    }

    companion object {

        private var mRepos: List<Repository>? = null
        fun newInstance(repos: List<Repository>): Fragment {
            mRepos = repos
            return RepoFragment()
        }
    }
}
