package com.nexachats.miniapp.di

import com.nexachats.miniapp.data.GitHubApiService
import com.nexachats.miniapp.data.GitHubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Created by Arshad Khan on 4/22/2025.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGitHubApi(): GitHubApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApiService::class.java)
    }

    @Provides
    fun provideRepository(api: GitHubApiService): GitHubRepository {
        return GitHubRepository(api)
    }
}