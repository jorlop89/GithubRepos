package com.jorlop89.githubrepos.data.remote.api

import com.google.gson.annotations.SerializedName
import com.jorlop89.githubrepos.model.Repo

data class RepoResponse(
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<Repo> = emptyList(),
    val nextPage: Int? = null
)
