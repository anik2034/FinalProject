package com.example.finalproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DataLoadViewModel : ViewModel() {
    private val _usersLiveData: MutableLiveData<NewsResponse> = MutableLiveData<NewsResponse>()
    val usersLiveData: LiveData<NewsResponse> = _usersLiveData
    private val networkService by lazy { NewsRepo() }

    fun loadNews() {

        //#BEEFC6

        viewModelScope.launch(Dispatchers.IO) {

            _usersLiveData.postValue(networkService.fetchNews("us"))

        }
    }
}
