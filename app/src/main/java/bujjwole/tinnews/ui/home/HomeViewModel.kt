package bujjwole.tinnews.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import bujjwole.tinnews.model.Article
import bujjwole.tinnews.repository.NewsRepository

class HomeViewModel(val newsRepository: NewsRepository): ViewModel() {

    private val countryInput: MutableLiveData<String> = MutableLiveData<String>()

    fun setCountryInput(country: String) = countryInput.setValue(country)

    fun getTopHeadlines() = Transformations.switchMap(countryInput, newsRepository::getTopHeadlines)

    fun setFavoriteArticleInput(article: Article) = newsRepository.favoriteArticle(article)
}