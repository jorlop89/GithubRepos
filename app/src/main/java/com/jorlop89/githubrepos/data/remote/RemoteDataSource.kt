package com.jorlop89.githubrepos.data.remote

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jorlop89.githubrepos.data.remote.api.GithubService
import com.jorlop89.githubrepos.model.RepoDTO
import java.lang.Exception

class RemoteDataSource(private val service: GithubService) : PagingSource<Int, RepoDTO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoDTO> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.getRepositories(pageNumber, PAGE_SIZE)
            val data = response.items
            var nextPageNumber: Int? = null
            if(response.nextPage != null){
                val uri = Uri.parse(response.nextPage.toString())
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }
            LoadResult.Page(
                data = data,
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