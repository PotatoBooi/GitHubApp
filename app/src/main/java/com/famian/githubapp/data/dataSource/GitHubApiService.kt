package com.famian.githubapp.data.dataSource

import com.famian.githubapp.data.dataSource.network.ConnectivityInterceptor
import com.famian.githubapp.data.model.entity.RepositoryResponse
import com.famian.githubapp.data.model.entity.User
import com.famian.githubapp.data.model.entity.UserSimple
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val API_URL = "https://api.github.com/"
const val API_TOKEN = "3d92747686bd6d32777ab5ee77229d1dd5394021"

interface GitHubApiService {
    @GET("users/{username}")
    fun getUser(
        @Path("username") userName: String
    ): Deferred<User>

    @GET("users/{username}/repos")
    fun getUserRepos(@Path("username") userName: String): Deferred<List<RepositoryResponse>>

    @GET("users/{username}/followers")
    fun getUserFollowers(@Path("username") userName: String): Deferred<List<UserSimple>>

    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): GitHubApiService {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    //       .addQueryParameter("APPID", API_KEY)

                    .build()
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", API_TOKEN)
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()
            val moshi = Moshi.Builder().build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(API_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(GitHubApiService::class.java)
        }
    }
}