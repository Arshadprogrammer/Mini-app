package com.nexachats.miniapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexachats.miniapp.data.GitHubRepository
import com.nexachats.miniapp.data.RepositoryModel
import com.nexachats.miniapp.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Created by Arshad Khan on 4/22/2025.
 */

@HiltViewModel
class GitHubViewModel @Inject constructor(
    private val repository: GitHubRepository
) : ViewModel()  {


    private val _repos = MutableLiveData<UIState<List<RepositoryModel>>>()
    val repos: LiveData<UIState<List<RepositoryModel>>> = _repos

    var currentPage = 1
    private var isLoading = false
    private var isLastPage = false
    init {
        Log.d("GitHubViewModel", "GitHubViewModel initialized!")
    }

    fun loadRepos(user: String) {
        if (isLoading || isLastPage) return
        isLoading = true

        viewModelScope.launch {
            val result = repository.getRepos(user, currentPage)
            if (result is UIState.Success && result.data!!.isEmpty()) {
                isLastPage = true
            } else if (result is UIState.Success) {
                currentPage++
            }
            _repos.postValue(result)
            isLoading = false
        }
    }

}