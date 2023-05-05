package com.example.finalproject
import android.os.Parcelable
import java.io.Serializable
import com.google.gson.annotations.SerializedName

data class NewsResponse (
    @SerializedName("status")
    var status: String?,
    @SerializedName("totalResults")
    var totalResults: Int?,
    @SerializedName("articles")
    var articles: List<ArticleResponse?>?,



): Serializable

class ArticleResponse (
    @SerializedName("source")
    var source: SourceResponse?,
    @SerializedName("author")
    var author: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("urlToImage")
    var urlToImage: String?,
    @SerializedName("publishedAt")
    var publishedAt: String?,
    @SerializedName("content")
    var content: String?,


    ) : Serializable

class SourceResponse(
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
) : Serializable



