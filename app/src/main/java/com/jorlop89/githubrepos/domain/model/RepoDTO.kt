package com.jorlop89.githubrepos.model

import com.google.gson.annotations.SerializedName

data class RepoDTO(
    val name: String,
    val description: String,
    /*@SerializedName("html_url")
    val urlRepository: String,*/
    /*@SerializedName("owner")
    val ownerData: OwnerDTO,*/
    val fork: Boolean
)