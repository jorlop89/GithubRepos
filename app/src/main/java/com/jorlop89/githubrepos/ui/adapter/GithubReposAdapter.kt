package com.jorlop89.githubrepos.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.jorlop89.githubrepos.databinding.RepoViewItemBinding
import com.jorlop89.githubrepos.model.RepoDTO
import javax.inject.Inject

class GithubReposAdapter @Inject constructor(): PagingDataAdapter<RepoDTO, GithubReposViewHolder>(RepoComparator) {

    private lateinit var mListener: GithubRepoClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubReposViewHolder {
        return GithubReposViewHolder(RepoViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), mListener)
    }

    override fun onBindViewHolder(holder: GithubReposViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    object RepoComparator: DiffUtil.ItemCallback<RepoDTO>(){
        override fun areItemsTheSame(oldItem: RepoDTO, newItem: RepoDTO): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: RepoDTO, newItem: RepoDTO): Boolean =
            oldItem == newItem

    }

    fun setOnItemClickListener(listener: GithubRepoClickListener){
        mListener = listener
    }

    interface GithubRepoClickListener {
        fun repoClicked(binding: Int, repo: RepoDTO): Boolean
    }
}