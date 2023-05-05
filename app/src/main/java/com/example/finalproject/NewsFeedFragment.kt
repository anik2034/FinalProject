package com.example.finalproject

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.finalproject.databinding.FiltersPageBinding
import com.example.finalproject.databinding.FragmentDataLoadBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NewsFeedFragment : Fragment() {

    private lateinit var rv: RecyclerView
    private  var category = ""
    private var searched = ""
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var articles : List<ArticleResponse>
    private lateinit var noResult: TextView
    private lateinit var businessButton: Button
    private lateinit var applyButton: Button
    private lateinit var search:SearchView
    private lateinit var submit:Button
    private lateinit var entertainmentButton: Button
    private lateinit var generalButton: Button
    private lateinit var healthButton: Button
    private lateinit var filterButton: FloatingActionButton
    private lateinit var filterBinding: FiltersPageBinding
    private lateinit var filtersPage: BottomSheetDialog
    private lateinit var binding: FragmentDataLoadBinding

    companion object {
        fun newInstance() = NewsFeedFragment()
    }

    private lateinit var viewModel: NewsFeedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDataLoadBinding.inflate(inflater, container, false)

        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv = binding.rv;
        rv.layoutManager = LinearLayoutManager(activity)
        filterButton = binding.filtersButton






        viewModel = ViewModelProvider(this).get(NewsFeedViewModel::class.java)

        viewModel.loadNews(category, searched)

        viewModel.usersLiveData.observe(viewLifecycleOwner, Observer {
            rv.adapter = context?.let { it1 ->
                articles = viewModel.usersLiveData.value?.articles as List<ArticleResponse>
                NewsAdapter(

                    it1, object : NewsAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {
                            viewModel.sendArticle(articles?.get(position), activity)

                        }

                    }, articles, binding

                )



            }


        })
        search = binding.searchView

        submit = binding.submit
        noResult = binding.no

        search.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                submit.visibility = View.VISIBLE
            } else {
                submit.visibility = View.GONE
            }
        }

        submit.setOnClickListener {
            searched  = search.query.toString()
            viewModel.loadNews(category, searched)
            search.clearFocus()
        }


        swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadNews(category, searched)
            swipeRefreshLayout.isRefreshing = false

        }





        filterButton.setOnClickListener {
            filtersPage = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
            filterBinding = FiltersPageBinding.inflate(layoutInflater, null, false)
            filtersPage.setContentView(filterBinding!!.root)
            filtersPage.show()
            businessButton = filterBinding.busineesButton
            entertainmentButton = filterBinding.entertainmentButton
            generalButton = filterBinding.generalButton
            healthButton = filterBinding.healthButton
            applyButton = filterBinding.applyButton

            businessButton.setOnClickListener {
                viewModel.applyFilter(
                    businessButton, entertainmentButton, generalButton, healthButton
                );

            }

            entertainmentButton.setOnClickListener {
                viewModel.applyFilter(
                    entertainmentButton, businessButton, generalButton, healthButton
                );

            }
            generalButton.setOnClickListener {
                viewModel.applyFilter(
                    generalButton, entertainmentButton, businessButton, healthButton
                );

            }
            healthButton.setOnClickListener {
                viewModel.applyFilter(
                    healthButton, generalButton, entertainmentButton, businessButton
                );

            }


            applyButton.setOnClickListener {

                if (viewModel.isBackgroundWhite(businessButton)) {

                    category="business"
                    viewModel.loadNews(category, searched)
                } else if (viewModel.isBackgroundWhite(entertainmentButton)) {

                    category="entertainment"
                    viewModel.loadNews(category, searched)
                } else if (viewModel.isBackgroundWhite(generalButton)) {

                    category="general"
                    viewModel.loadNews(category, searched)

                } else if (viewModel.isBackgroundWhite(healthButton)) {

                    category="health"
                    viewModel.loadNews(category, searched)

                }
                filtersPage.dismiss()
            }


        }


    }


}