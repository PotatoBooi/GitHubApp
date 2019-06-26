package com.famian.githubapp.data.model.entity

data class Repository(val title: String, val author: String, var language: String = "", val stars: Int)

internal val RepositoryResponse.toRepositoryModel
    get() = Repository(name, owner.login, language ?: "", stargazersCount)