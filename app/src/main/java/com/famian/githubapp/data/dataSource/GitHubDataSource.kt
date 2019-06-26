package com.famian.githubapp.data.dataSource

import com.famian.githubapp.data.model.entity.RepositoryResponse
import com.famian.githubapp.data.model.entity.User
import com.famian.githubapp.data.model.entity.UserSimple

class GitHubDataSource(private val apiService: GitHubApiService) {
    suspend fun getUser(userName: String): User {
        return apiService.getUser(userName).await()
    }

    suspend fun getUserRepos(userName: String): List<RepositoryResponse> {
        return apiService.getUserRepos(userName).await()
    }

    suspend fun getUserFollowers(userName: String): List<UserSimple> {
        return apiService.getUserFollowers(userName).await()
    }
}