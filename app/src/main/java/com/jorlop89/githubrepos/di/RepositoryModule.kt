package com.jorlop89.githubrepos.di

import com.jorlop89.githubrepos.data.GithubRepository
import com.jorlop89.githubrepos.data.remote.api.GithubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideGithubService(retrofit: Retrofit): GithubService {
        return retrofit.create(GithubService::class.java)
    }

    @Provides
    fun provideGithubRepository(): GithubRepository {
        return GithubRepository(GithubService.getInstance())
    }
}