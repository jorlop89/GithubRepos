package com.jorlop89.githubrepos.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jorlop89.githubrepos.R
import com.jorlop89.githubrepos.databinding.ActivityMainBinding
import com.jorlop89.githubrepos.model.RepoDTO
import com.jorlop89.githubrepos.ui.adapter.GithubReposAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    private var adapter: GithubReposAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mainViewModel = mainViewModel
        initAdapter()
        collectUIData()
    }

    private fun initAdapter(){
        adapter = GithubReposAdapter()
        binding.rvRepos.adapter = adapter
        adapter?.setOnItemClickListener(object: GithubReposAdapter.GithubRepoClickListener{
            override fun repoClicked(binding: Int, repo: RepoDTO): Boolean {
                MaterialAlertDialogBuilder(this@MainActivity)
                    .setTitle(resources.getString(R.string.title_alert_dialog))
                    .setMessage(resources.getString(R.string.message_alert_dialog))
                    .setNegativeButton(resources.getString(R.string.go_to_repository)) { dialog, which ->
                        openBrowserIntent(repo.urlRepository)
                    }
                    .setPositiveButton(resources.getString(R.string.go_to_owner)) { dialog, which ->
                        openBrowserIntent(repo.ownerData.urlOwner)
                    }
                    .show()
                return true
            }
        })
    }

    private fun collectUIData(){
        lifecycleScope.launch{
            mainViewModel.getRepositories().collectLatest {
                repo -> adapter?.submitData(repo)
            }
        }
    }

    private fun openBrowserIntent(url: String){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}