package bujjwole.tinnews.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import bujjwole.tinnews.model.Article

@Dao
interface ArticleDao {

    @Insert
    fun saveArticle(article: Article)

    @Query("SELECT * FROM article")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    fun deleteArticle(article: Article)

}