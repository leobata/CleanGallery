package com.leobata.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.leobata.data.local.dao.CommentDao
import com.leobata.data.local.dao.PhotoDao
import com.leobata.data.local.di.ApplicationScope
import com.leobata.data.local.model.Comment
import com.leobata.data.local.model.Photo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Photo::class, Comment::class], version = 1)
abstract class GalleryDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
    abstract fun commentDao(): CommentDao
    class Callback @Inject constructor(
        private val database: Provider<GalleryDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val photoDao = database.get().photoDao()
            val commentDao = database.get().commentDao()
            applicationScope.launch {
            }
        }
    }
}