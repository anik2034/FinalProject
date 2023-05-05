package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.example.finalproject.databinding.FragmentDataLoadBinding
import com.example.finalproject.databinding.FragmentDetailsPageBinding
import com.squareup.picasso.Picasso


class DetailsPageFragment : Fragment() {

    private lateinit var binding: FragmentDetailsPageBinding
    private lateinit var article: ArticleResponse
    private lateinit var source: TextView
    private lateinit var author: TextView
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var img: ImageView
    private lateinit var back: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        article =
            arguments?.getSerializable("article", ArticleResponse::class.java) as ArticleResponse
        source = binding.sourceText
        author = binding.authorText
        title = binding.titleText
        description = binding.descriptionText
        img = binding.image
        back = binding.backButton


        println(article.description)
        source.text = "Source: " + article.source?.name ?: ""
        author.text = "Author: " + article.author
        title.text = article.title
        description.text = article.description
        Picasso.with(context).load(article.urlToImage).into(img);

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.supportFragmentManager?.beginTransaction()?.remove(getThis())
                        ?.commit()

                }
            })
        back.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(getThis())?.commit()

        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsPageBinding.inflate(inflater, container, false)


        return binding.root;
    }

    private fun getThis(): DetailsPageFragment {
        return this
    }

    companion object {

        fun newInstance() = DetailsPageFragment()
    }
}