package com.jorlop89.githubrepos.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jorlop89.githubrepos.data.GithubRepository
import com.jorlop89.githubrepos.model.RepoDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: GithubRepository) : ViewModel(){

    private var currentResult: Flow<PagingData<RepoDTO>>? = null

    fun getRepositories(): Flow<PagingData<RepoDTO>> {
        val result : Flow<PagingData<RepoDTO>> = repository.getRepositories().cachedIn(viewModelScope)
        currentResult = result
        return result
    }
}