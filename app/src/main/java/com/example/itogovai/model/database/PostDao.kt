package com.example.itogovai.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface PostDao {
    @Query("SELECT * FROM POST")
    suspend fun qetPosts()

    @Insert
    suspend fun insertPost(post: Post)
    @Insert
    suspend fun insertPost(vararg post: Post)
    @Update
    suspend fun updatePost(post: Post)
    @Delete
    suspend fun deletePost(post: Post)
}