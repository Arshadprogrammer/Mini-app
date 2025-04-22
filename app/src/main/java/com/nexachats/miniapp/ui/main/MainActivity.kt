package com.nexachats.miniapp.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nexachats.miniapp.R
import com.nexachats.miniapp.utils.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var adapter: GitHubAdapter
    private val viewModel: GitHubViewModel by viewModels()
    private val user = "google"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = GitHubAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerRepos)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        Log.d("HiltDebug", "Before accessing viewModel")
        viewModelFetching()


        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                if (!rv.canScrollVertically(1)) {
                    viewModel.loadRepos(user)
                }
            }
        })

        viewModel.loadRepos(user)
    }

    private fun viewModelFetching() {
        viewModel.repos.observe(this) { result ->
            when (result) {
                is UIState.Loading -> { /* show loader */
                }

                is UIState.Success -> adapter.addItems(result.data!!)
                is UIState.Error -> Toast.makeText(this, result.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
