package com.famian.githubapp.data.model.entity

import com.squareup.moshi.Json

data class UserSimple(
    @field:Json(name = "login")
    val login: String,
    @field:Json(name = "avatar_url")
    val avatarUrl: String
)