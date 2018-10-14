package com.albertjsoft.githubkanban.model

import com.google.gson.annotations.SerializedName

/**
 * Created by albertj on 13/10/2018.
 */
data class Repository(
        @SerializedName("name") var name: String? = null,
        @SerializedName("owner") var user: User? = null
)