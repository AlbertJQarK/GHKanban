package com.albertjsoft.githubkanban.model

import com.google.gson.annotations.SerializedName

/**
 * Created by albertj on 13/10/2018.
 */
data class Repository(
        @SerializedName("id") private var id: Int? = null,
        @SerializedName("name") private var name: String? = null,
        @SerializedName("login") private var login: String? = null
)