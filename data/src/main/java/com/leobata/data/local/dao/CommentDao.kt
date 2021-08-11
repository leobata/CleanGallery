package com.leobata.data.local.dao

import androidx.room.*
import com.leobata.data.local.model.Comment
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: Comment): Long

    @Transaction
    @Query("SELECT * FROM comment_table")
    fun getComments(): Flow<List<Comment>>

    @Transaction
    @Query("SELECT * FROM comment_table WHERE id = :id")
    suspend fun getCommentById(id: Long): Comment?

    @Transaction
    @Query("SELECT * FROM comment_table WHERE photoId = :photoId")
    fun getCommentByPhotoId(photoId: Long): Flow<List<Comment>>

    @Update
    suspend fun updateComment(comment: Comment)

    @Query("DELETE FROM comment_table WHERE id = :id")
    suspend fun deleteCommentById(id: Long)

    @Query("DELETE FROM comment_table")
    suspend fun deleteAllComments()
}