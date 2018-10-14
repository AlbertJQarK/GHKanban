package com.albertjsoft.githubkanban.ui.main

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView

import com.albertjsoft.githubkanban.R
import com.albertjsoft.githubkanban.data.api.APIBuilder
import com.albertjsoft.githubkanban.data.api.APIService
import com.albertjsoft.githubkanban.model.Repository
import com.albertjsoft.githubkanban.model.User
import com.albertjsoft.githubkanban.ui.main.adapter.TabsAdapter

import com.bumptech.glide.Glide

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by albertj on 14/10/2018.
 */
class PresenterMain(context: Context) {

    private var mContext: Context? = null
    private var mRepos: List<Repository>? = null

    init {
        mContext = context
    }

    private fun initializeTabs(){
        val viewPager = (mContext as AppCompatActivity).findViewById(R.id.viewpager) as ViewPager
        val tabLayout = (mContext as AppCompatActivity).findViewById(R.id.tabs) as TabLayout
        viewPager.adapter = TabsAdapter((mContext as AppCompatActivity).
                supportFragmentManager, mRepos!!)
        tabLayout.setupWithViewPager(viewPager)
    }

    fun getUserData(username: String){
        val getUserData = APIBuilder.client.create(APIService::class.java).
                getUserData(username)
        getUserData.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {

                if(response.body() != null) {
                    val user: User = response.body() as User
                    (mContext as AppCompatActivity).
                            findViewById<TextView>(R.id.tv_name).text = user.name as String
                    (mContext as AppCompatActivity).
                            findViewById<TextView>(R.id.tv_username).text = user.login as String
                    Glide.with(mContext!!).load(user.avatar_url).
                            into((mContext as AppCompatActivity).findViewById<ImageView>(R.id.profile_photo))
                }else{
                    wrongUser()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                networkError()
                call.cancel()
            }
        })
    }

    fun getUserRepos(username: String){
        val getUserRepos = APIBuilder.client.create(APIService::class.java).
                getUserRepos(username)
        getUserRepos.enqueue(object : Callback<List<Repository>> {
            override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                mRepos = response.body() as List<Repository>
                initializeTabs()
            }

            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                call.cancel()
            }
        })
    }

    fun wrongUser(){
        val builder = AlertDialog.Builder(mContext!!)
        builder.setTitle(R.string.wrong_user_title)
        builder.setMessage(R.string.wrong_user_message)
        builder.setPositiveButton(R.string.ok){_, _ ->
            (mContext as AppCompatActivity).onBackPressed()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun networkError(){
        val builder = AlertDialog.Builder(mContext!!)
        builder.setTitle(R.string.network_error_title)
        builder.setMessage(R.string.network_error_message)
        builder.setPositiveButton(R.string.ok){_, _ ->
            (mContext as AppCompatActivity).onBackPressed()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}