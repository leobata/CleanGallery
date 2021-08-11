package com.leobata.data.local.dao

import androidx.room.*
import com.leobata.data.local.model.Photo
import com.leobata.data.local.model.PhotoWithComments
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(photo: Photo): Long

    @Transaction
    @Query("SELECT * FROM photo_table ORDER BY title ASC")
    fun getPhotos(): Flow<List<Photo>>

    @Query("SELECT * FROM photo_table WHERE id = :id")
    suspend fun getPhotoById(id: Long): Photo?

    @Update
    suspend fun updatePhoto(photo: Photo)

    @Query("DELETE FROM photo_table WHERE id = :id")
    suspend fun deletePhotoById(id: Long)

    @Query("DELETE FROM photo_table")
    suspend fun deleteAllPhotos()

    @Transaction
    @Query("SELECT * from photo_table")
    fun getAllPhotosWithComments(): List<PhotoWithComments>

    @Transaction
    @Query("SELECT * FROM photo_table WHERE id = :id")
    suspend fun getPhotoWithCommentsById(id: Long): PhotoWithComments?

}