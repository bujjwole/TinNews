package bujjwole.tinnews

import android.app.Application
import androidx.room.Room
import bujjwole.tinnews.database.TinNewsDatabase

class TinNewsApplication: Application() {

    companion object{
        private lateinit var database: TinNewsDatabase

        fun getDatabase(): TinNewsDatabase = database
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this, TinNewsDatabase::class.java, "tinnews_db").build()
    }

}