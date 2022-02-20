package com.jorlop89.githubrepos.ui.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jorlop89.githubrepos.R
import com.jorlop89.githubrepos.databinding.RepoViewItemBinding
import com.jorlop89.githubrepos.model.RepoDTO

class GithubRepoViewHolder(private val binding: RepoViewItemBinding, listener: GithubReposAdapter.GithubRepoClickListener): RecyclerView.ViewHolder(binding.root) {


    var mListener: GithubReposAdapter.GithubRepoClickListener = listener


    fun bind(repo: RepoDTO){
        binding.tvRepoName.text = repo.name
        binding.tvLoginOwner.text = repo.ownerData.loginOwner
        binding.tvRepoDescription.text = repo.description
        paintIfIRepoIsAFork(repo)

        /*itemView.setOnClickListener{
            mListener.repoClicked(layoutPosition, repo)
        }*/
        itemView.setOnLongClickListener {
            mListener.repoClicked(layoutPosition, repo)
        }

    }

    private fun paintIfIRepoIsAFork(repo: RepoDTO) {
        if (repo.fork) {
            binding.clItem.setBackgroundColor(
                ContextCompat.getColor(
                    binding.clItem.context,
                    R.color.teal_200
                )
            )
            binding.tvRepoName.setBackgroundColor(
                ContextCompat.getColor(
                    binding.tvRepoName.context,
                    R.color.teal_200
                )
            )
            binding.tvLoginOwner.setBackgroundColor(
                ContextCompat.getColor(
                    binding.tvLoginOwner.context,
                    R.color.teal_200
                )
            )
            binding.tvRepoDescription.setBackgroundColor(
                ContextCompat.getColor(
                    binding.tvRepoDescription.context,
                    R.color.teal_200
                )
            )
        }
    }
}

