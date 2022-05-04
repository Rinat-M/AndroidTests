package com.geekbrains.tests.di

import com.geekbrains.tests.BuildConfig
import com.geekbrains.tests.repository.GitHubApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    private const val BASE_URL = "https://api.github.com"

    fun getOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder().apply {
            getLoggerInterceptor()?.let {
                this.addInterceptor(it)
            }
        }

        return httpClient.build()
    }

    private fun getLoggerInterceptor(): HttpLoggingInterceptor? =
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        } else {
            null
        }

    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getGithubApiService(retrofit: Retrofit): GitHubApi =
        retrofit.create(GitHubApi::class.java)

}