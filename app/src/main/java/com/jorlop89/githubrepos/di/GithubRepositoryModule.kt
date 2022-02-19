package com.jorlop89.githubrepos.di

import com.jorlop89.githubrepos.data.GithubRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GithubRepositoryModule {
    @Binds
    abstract fun bindGithubRepository(repository: GithubRepository): GithubRepository
}