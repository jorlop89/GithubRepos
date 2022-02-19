package com.jorlop89.githubrepos.model

import com.google.gson.annotations.SerializedName

data class OwnerData(
    @SerializedName("login")
    val loginOwner: String,
    @SerializedName("html_url")
    val urlOwner: String
)