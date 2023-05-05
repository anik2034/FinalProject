package com.example.finalproject


import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.finalproject.databinding.FragmentDataLoadBinding
import com.example.finalproject.databinding.NewsCardBinding
import com.squareup.picasso.Picasso



class NewsAdapter(val c: Context,val listener: onItemClickListener, val list: List<ArticleResponse?>?, val binding: FragmentDataLoadBinding) :

    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private lateinit var  context: Context
    private lateinit var bindingFragment: FragmentDataLoadBinding
    private lateinit var mListener: onItemClickListener

    init{
        mListener =  listener
        bindingFragment = binding
        if(itemCount == 0){
            bindingFragment.no.isVisible = true
        }
        else
            bindingFragment.no.isVisible = false


        }

    inner class NewsViewHolder(val b : NewsCardBinding, listener: onItemClickListener) : RecyclerView.ViewHolder(b.root) {

       init{

           itemView.setOnClickListener {
               listener.onItemClick(adapterPosition)

           }
       }
        fun bindItem(news : ArticleResponse){
                b.sourceName.text = news.source?.name ?:""
                b.newsTitle.text =news.title;
                b.author.text = news.author

            Picasso.with(c).load(news.urlToImage).into(b.img);

            }

    }



    interface onItemClickListener{
        fun onItemClick(position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): NewsViewHolder {

        return NewsViewHolder(NewsCardBinding.inflate(LayoutInflater.from(parent.context),parent,false), mListener)

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