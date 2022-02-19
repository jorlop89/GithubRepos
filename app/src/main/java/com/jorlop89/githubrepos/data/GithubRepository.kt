package com.jorlop89.githubrepos.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jorlop89.githubrepos.data.remote.RemoteDataSource
import com.jorlop89.githubrepos.data.remote.api.GithubService
import com.jorlop89.githubrepos.model.Repo
import kotlinx.coroutines.flow.Flow

class GithubRepository(private val githubService: GithubService) {

    fun getRepositories(): Flow<PagingData<Repo>> {
        return Pager(config = PagingConfig(pageSize = NETWORK_PAGE_SIZE,  enablePlaceholders = false),
            pagingSourceFactory = { RemoteDataSource(githubService) }
        ).flow
    }

    companion object{
        const val NETWORK_PAGE_SIZE = 10
    }
}