package com.albertjsoft.githubkanban.model

import com.google.gson.annotations.SerializedName

/**
 * Created by albertj on 14/10/2018.
 */
data class User(
        @SerializedName("login") var login: String? = null,
        @SerializedName("avatar_url") var avatar_url: String? = null,
        @SerializedName("name") var name: String? = null
)