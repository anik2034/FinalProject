package com.example.finalproject

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {


    @GET("https://newsapi.org/v2/top-headlines?apiKey=bbc368f1634b485faeeb058f6b326f91")
    suspend fun fetchNews(@Query("country") country:String, @Query("category")  category: String,
            @Query("q") q : String):NewsResponse
}