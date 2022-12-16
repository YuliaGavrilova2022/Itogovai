package com.example.itogovai.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Post(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int,
    @SerializedName("userId")
    val uzerId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
)