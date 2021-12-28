package bujjwole.tinnews.repository

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import bujjwole.tinnews.TinNewsApplication
import bujjwole.tinnews.database.TinNewsDatabase
import bujjwole.tinnews.model.Article
import bujjwole.tinnews.model.NewsResponse
import bujjwole.tinnews.network.NewsApi
import bujjwole.tinnews.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class NewsRepository(val context: Context?) {

    private lateinit var newsApi: NewsApi
    private val database: TinNewsDatabase = TinNewsApplication.getDatabase()

    private class FavoriteAsyncTask(val database: TinNewsDatabase, val liveData: MutableLiveData<Boolean>): AsyncTask<Article, Void, Boolean>(){

        override fun doInBackground(vararg p0: Article?): Boolean {
            val article: Article? = p0[0]

            if (article != null) {
                database.articleDao().saveArticle(article)
                return true
            }

            return false
        }

        override fun onPostExecute(result: Boolean?) {
            liveData.value = result
        }

    }

    fun favoriteArticle(article: Article): LiveData<Boolean>{
        val resultLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
        FavoriteAsyncTask(database, resultLiveData).execute(article)

        return resultLiveData
    }

    fun getAllSavedArticles(): LiveData<List<Article>> = database.articleDao().getAllArticles()

    fun deleteSavedArticle(article: Article) = AsyncTask.execute{database.articleDao().deleteArticle(article)}

    fun getTopHeadlines(country: String): LiveData<NewsResponse>{
        if (context == null) throw Exception("Cannot get live data due to null content")
        else newsApi = RetrofitClient.newInstance(context).create(NewsApi::class.java)
        val topHeadlinesLiveData: MutableLiveData<NewsResponse> = MutableLiveData<NewsResponse>()

        newsApi.getTopHeadlines(country).enqueue(object: Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) topHeadlinesLiveData.setValue(response.body())
                else  topHeadlinesLiveData.setValue(null)
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                topHeadlinesLiveData.setValue(null)
            }
        })

        return topHeadlinesLiveData
    }

    fun searchNews(query: String): LiveData<NewsResponse>{
        if (context == null) throw Exception("Cannot search news due to null content")
        else newsApi = RetrofitClient.newInstance(context).create(NewsApi::class.java)
        val allNewsData: MutableLiveData<NewsResponse> = MutableLiveData<NewsResponse>()

        newsApi.getEverything(query, 40).enqueue(object: Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) allNewsData.setValue(response.body())
                else  allNewsData.setValue(null)
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                allNewsData.setValue(null)
            }
        })

        return allNewsData
    }
}