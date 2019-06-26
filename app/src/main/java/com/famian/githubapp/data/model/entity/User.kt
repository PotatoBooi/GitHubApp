package com.famian.githubapp.data.model.entity

import com.squareup.moshi.Json


data class User(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "login")
    val login: String,
    @field:Json(name = "bio")
    val description: String,
    @field:Json(name = "avatar_url")
    val avatarUrl: String,
    @field:Json(name = "company")
    val company: String?,
    @field:Json(name = "location")
    val location: String?,
    @field:Json(name = "email")
    val email: String,
    @field:Json(name = "following")
    val following: Int,
    @field:Json(name = "followers")
    val followers: Int,
    @field:Json(name = "public_repos")
    val repos: Int,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "repos_url")
    val reposUrl: String
)