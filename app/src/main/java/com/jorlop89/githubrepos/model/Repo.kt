package com.jorlop89.githubrepos.model

import com.google.gson.annotations.SerializedName

data class Repo(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("html_url")
    val urlRepository: String,
    @SerializedName("owner")
    val ownerData: OwnerData,
    @SerializedName("fork")
    val fork: Boolean
)