package com.jorlop89.githubrepos.data.remote.api

import com.google.gson.annotations.SerializedName
import com.jorlop89.githubrepos.model.RepoDTO

data class RepoResponse(
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<RepoDTO> = emptyList(),
    val nextPage: Int? = null
)
