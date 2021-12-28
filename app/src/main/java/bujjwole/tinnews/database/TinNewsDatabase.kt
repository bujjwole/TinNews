package bujjwole.tinnews.database

import androidx.room.Database
import androidx.room.RoomDatabase
import bujjwole.tinnews.model.Article

@Database(entities = [Article::class], version=1, exportSchema = false)
abstract class TinNewsDatabase: RoomDatabase(){
    abstract fun articleDao(): ArticleDao
}