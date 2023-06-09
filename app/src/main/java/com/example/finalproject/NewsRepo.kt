package com.example.finalproject

class NewsRepo {
    private val retrofit by lazy { RetrofitHelper.getInstance() }
     suspend fun fetchNews(country: String): NewsResponse {
        val apiService = retrofit.create(NewsApiService::class.java)
        return apiService.fetchNews(country)
    }
}