package com.nexachats.miniapp.data

import com.nexachats.miniapp.utils.UIState
import retrofit2.Response
import javax.inject.Inject

/**
 * @Created by Arshad Khan on 4/22/2025.
 */
class GitHubRepository @Inject constructor(private val apiService: GitHubApiService) {
    suspend fun getRepos(user: String, page: Int): UIState<List<RepositoryModel>> {
        return try {
            val response = apiService.getRepos(user, page)
            if (response.isSuccessful) {
                UIState.Success(response.body() ?: emptyList())
            } else {
                UIState.Error("Error ${response.code()}: ${response.message()}")
            }
        } catch (e: Exception) {
            UIState.Error("Exception: ${e.localizedMessage}")
        }
    }
}