package bujjwole.tinnews.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bujjwole.tinnews.ui.home.HomeViewModel
import bujjwole.tinnews.ui.save.SaveViewModel
import bujjwole.tinnews.ui.search.SearchViewModel

class NewsViewModelFactory(val repository: NewsRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) return HomeViewModel(repository) as T
        else if (modelClass.isAssignableFrom(SearchViewModel::class.java)) return SearchViewModel(repository) as T
        else if (modelClass.isAssignableFrom(SaveViewModel::class.java)) return SaveViewModel(repository) as T
        else throw IllegalStateException("Unknown ViewModel")
    }

}