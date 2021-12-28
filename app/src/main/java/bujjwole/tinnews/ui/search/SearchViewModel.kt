package bujjwole.tinnews.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import bujjwole.tinnews.repository.NewsRepository

class SearchViewModel (val newsRepository: NewsRepository): ViewModel(){
    private val searchInput: MutableLiveData<String> = MutableLiveData<String>()

    fun setSearchInput(query: String) = searchInput.setValue(query)

    fun searchNews() = Transformations.switchMap(searchInput, newsRepository::searchNews)

}