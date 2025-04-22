package com.nexachats.miniapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nexachats.miniapp.R
import com.nexachats.miniapp.data.RepositoryModel

/**
 * @Created by Arshad Khan on 4/22/2025.
 */
class GitHubAdapter : RecyclerView.Adapter<GitHubAdapter.ViewHolder>() {
    private val list = mutableListOf<RepositoryModel>()

    fun addItems(newRepos: List<RepositoryModel>) {
        val start = list.size
        list.addAll(newRepos)
        notifyItemRangeInserted(start, newRepos.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repository, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = list[position]
        holder.bind(repo)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvRepoName = view.findViewById<TextView>(R.id.tvRepoName)
        private val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
        private val tvLanguage = view.findViewById<TextView>(R.id.tvLanguage)
        private val tvStars = view.findViewById<TextView>(R.id.tvStars)
        private val tvForks = view.findViewById<TextView>(R.id.tvForks)

        fun bind(repo: RepositoryModel) {
            tvRepoName.text = repo.name
            tvDescription.text = repo.description ?: "No description available"
            tvLanguage.text = repo.language ?: "Unknown"
            tvStars.text = "⭐ ${repo.stargazers_count}"
            tvForks.text = "🍴 ${repo.forks_count}"
        }
    }
}
