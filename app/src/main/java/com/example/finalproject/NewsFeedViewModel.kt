package com.example.finalproject

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsFeedViewModel : ViewModel() {
    private val _usersLiveData: MutableLiveData<NewsResponse> = MutableLiveData<NewsResponse>()
    val usersLiveData: LiveData<NewsResponse> = _usersLiveData
    private val networkService by lazy { NewsRepo() }

    fun loadNews(category: String, searched : String) {
        viewModelScope.launch(Dispatchers.IO) {

            _usersLiveData.postValue(networkService.fetchNews("us", category, searched))


        }

    }
    fun refresh(category: String, searched : String){
        viewModelScope.launch(Dispatchers.IO) {

            _usersLiveData.postValue(networkService.fetchNews("us", category, searched))

        }
    }

//    private fun loadNewsCategory(category: String, searched : String) {
//        viewModelScope.launch(Dispatchers.IO) {
//
//            _usersLiveData.postValue(networkService.fetchNews("us", category, searched))
//
//        }
//    }
//
//  fun loadNewsSearched(q: String, category:String) {
//
//
//        viewModelScope.launch(Dispatchers.IO) {
//
//            _usersLiveData.postValue(networkService.fetchNews("us", category, q))
//
//        }
//
//    }
//
//    fun loadNewsBusiness(searched : String) {
//        loadNewsCategory("business",searched)
//    }
//
//    fun loadNewsEntertainment(searched : String) {
//        loadNewsCategory("entertainment", searched)
//    }
//
//    fun loadNewsGeneral(searched : String) {
//        loadNewsCategory("general",searched)
//    }
//
//    fun loadNewsHealth(searched : String) {
//        loadNewsCategory("health",searched)
//    }


    fun applyFilter(applied: Button, other1: Button, other2: Button, other3: Button) {

        setButtonColor(applied, "#FFFFFFFF", "#FF23814D")
        setButtonColor(other1, "#FF23814D", "#FFFFFFFF")
        setButtonColor(other2, "#FF23814D", "#FFFFFFFF")
        setButtonColor(other3, "#FF23814D", "#FFFFFFFF")


    }

    fun isBackgroundWhite(button: Button): Boolean {
        val backgroundTintList = button.backgroundTintList
        val backgroundTintColor =
            backgroundTintList?.getColorForState(button.drawableState, 0) ?: Color.TRANSPARENT

        val backgroundTintHexString = Integer.toHexString(backgroundTintColor)

        println(backgroundTintHexString)
        return backgroundTintHexString.equals("ffffffff")
    }

    private fun setButtonColor(button: Button, backgroundColor: String, textColor: String) {
        button.backgroundTintList = ColorStateList.valueOf(Color.parseColor(backgroundColor))
        button.setTextColor(Color.parseColor(textColor))
    }

    fun sendArticle(article: ArticleResponse?, activity: FragmentActivity?) {
        val bundle = Bundle()
        bundle.putSerializable("article", article)

        val details = DetailsPageFragment()
        details.arguments = bundle

        val ft = activity!!.supportFragmentManager.beginTransaction()
        ft.add(R.id.f1, details, details.javaClass.name)
        ft.commit()
    }




}
