package com.jorlop89.githubrepos.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jorlop89.githubrepos.data.remote.api.GithubService
import com.jorlop89.githubrepos.model.RepoDTO

class RemoteDataSource(private val service: GithubService) : PagingSource<Int, RepoDTO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoDTO> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.getRepositories(pageNumber, PAGE_SIZE)
            var nextPageNumber: Int? = null
            if(!response.isNullOrEmpty()){
                nextPageNumber = pageNumber + 1
            }
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RepoDTO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object{
        const val PAGE_SIZE = 10
    }

}