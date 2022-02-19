package com.jorlop89.githubrepos.data.remote.api

import com.google.gson.annotations.SerializedName
import com.jorlop89.githubrepos.model.RepoDTO

data class RepoResponse(
    val items: List<RepoDTO> = listOf(),
    val nextPage: Int? = null
)
