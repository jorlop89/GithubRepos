package com.jorlop89.githubrepos.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jorlop89.githubrepos.databinding.ActivityMainBinding
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
        initView()
        collectUIData()
    }

    private fun initView(){
        adapter = GithubReposAdapter()
        binding.rvRepos.adapter = adapter
    }

    private fun collectUIData(){
        lifecycleScope.launch{
            mainViewModel.getRepositories().collectLatest {
                repo -> adapter?.submitData(repo)
            }
        }
    }
}