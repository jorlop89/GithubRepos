package com.jorlop89.githubrepos.di

import com.jorlop89.githubrepos.data.GithubRepository
import com.jorlop89.githubrepos.data.remote.api.GithubService

class RepositoryModule {

    /**
     * Creates an instance of [GithubRepository] based on the [GithubService] and a
     * [GithubLocalCache]
     */
    private fun provideGithubRepository(): GithubRepository {
        return GithubRepository(GithubService.getInstance())
    }
}