package com.example.finalproject

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.FragmentDataLoadBinding

class DataLoad : Fragment() {

    private lateinit var rv: RecyclerView
    private lateinit var binding:FragmentDataLoadBinding

    companion object {
        fun newInstance() = DataLoad()
    }

    private lateinit var viewModel: DataLoadViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = FragmentDataLoadBinding.inflate(inflater, container, false)

        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv = binding.rv;
        rv.layoutManager = LinearLayoutManager(activity)


        viewModel = ViewModelProvider(this).get(DataLoadViewModel::class.java)
        viewModel.loadNews()
        viewModel.usersLiveData.observe(viewLifecycleOwner,Observer{
            rv.adapter = context?.let { it1 -> NewsAdapter(it1,
                viewModel.usersLiveData.value?.articles
            ) }
        })






    }

}