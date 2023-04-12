package com.example.finalproject


import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.finalproject.databinding.FragmentDataLoadBinding
import com.example.finalproject.databinding.NewsCardBinding
import com.squareup.picasso.Picasso



class NewsAdapter(val c: Context, val list: List<ArticleResponse?>?) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    inner class NewsViewHolder(val b : NewsCardBinding) : RecyclerView.ViewHolder(b.root) {
        fun bindItem(news : ArticleResponse){
                b.sourceName.text = news.source?.name ?:""
                b.newsTitle.text =news.title;
                b.author.text = news.author

            Picasso.with(c).load(news.urlToImage).into(b.img);

            }
    }

    private lateinit var  context: Context

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): NewsViewHolder {
        return NewsViewHolder(NewsCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newList = list?.get(position)

        if (newList != null) {
            holder.bindItem(newList);
        }




    }
}