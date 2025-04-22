package com.nexachats.miniapp.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @Created by Arshad Khan on 4/22/2025.
 */
interface GitHubApiService {
    @GET("users/{user}/repos")
    suspend fun getRepos(
        @Path("user") user: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 10
    ): Response<List<RepositoryModel>>
}