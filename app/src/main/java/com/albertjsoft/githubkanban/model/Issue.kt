package com.albertjsoft.githubkanban.model

import com.google.gson.annotations.SerializedName

/**
 * Created by albertj on 13/10/2018.
 */
data class Issue(
    @SerializedName("title") private var title: String? = null,
    @SerializedName("issue") private var issue: String? = null,
    @SerializedName("info") private var info: String? = null
)