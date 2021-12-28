package bujjwole.tinnews.ui.save

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import bujjwole.tinnews.model.Article
import bujjwole.tinnews.repository.NewsRepository

class SaveViewModel(val newsRepository: NewsRepository): ViewModel() {

    fun getAllSavedArticles(): LiveData<List<Article>> = newsRepository.getAllSavedArticles()

    fun deleteSavedArticle(article: Article) = newsRepository.deleteSavedArticle(article)
}