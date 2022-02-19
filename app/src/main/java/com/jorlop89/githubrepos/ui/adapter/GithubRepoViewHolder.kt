package com.jorlop89.githubrepos.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.jorlop89.githubrepos.databinding.RepoViewItemBinding
import com.jorlop89.githubrepos.model.RepoDTO

class GithubRepoViewHolder(private val binding: RepoViewItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(repo: RepoDTO){
        binding.tvRepoName.text = repo.name
        binding.tvLoginOwner.text = repo.ownerData.loginOwner
        binding.tvRepoDescription.text = repo.description
    }
}