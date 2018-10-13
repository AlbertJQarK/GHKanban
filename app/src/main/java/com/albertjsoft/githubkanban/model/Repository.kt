package com.albertjsoft.githubkanban.model

import com.google.gson.annotations.SerializedName

/**
 * Created by albertj on 13/10/2018.
 */
data class Repository(
        @SerializedName("name") private var name: String? = null,
        @SerializedName("id") private var id: Int? = null,
        @SerializedName("login") private var login: String? = null,
        @SerializedName("avatar_url") private var avatarUrl: String? = null
)