package com.jorlop89.githubrepos.data.remote.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("users/$USERNAME/repos")
    suspend fun getRepositories(
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): RepoResponse

    companion object {
        private const val BASE_URL = "https://api.github.com/"
        private const val USERNAME = "jorlop89"

        fun getInstance(): GithubService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder().addInterceptor(logger).build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubService::class.java)
        }
    }

}