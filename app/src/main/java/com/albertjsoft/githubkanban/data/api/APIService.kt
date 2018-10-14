package com.albertjsoft.githubkanban.data.api

import retrofit2.http.GET
import retrofit2.http.Path

import com.albertjsoft.githubkanban.model.User
import com.albertjsoft.githubkanban.model.Repository
import retrofit2.Call

/**
 * Created by albertj on 14/10/2018.
 */
interface APIService {

    @GET("/users/{username}")
    fun searchUserData(@Path("username") username: String): Call<User>

    @GET("/users/{username}/repos")
    fun searchUserRepos(@Path("username") username: String): Call<List<Repository>>

}