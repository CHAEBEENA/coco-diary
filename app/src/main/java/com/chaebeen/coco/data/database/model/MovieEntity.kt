package com.chaebeen.coco.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    @ColumnInfo(name = "poster_url")
    val posterUrl: String
    /*
    val directors: List<String>,
    val actors: List<String>,
    val rate: Double,
    val review: String,
    val people: List<String>, *//* TODO(naming: companions) *//*
    val date: String, *//* TODO(type: LocalDateTime) *//*
    val location: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Long,
    @ColumnInfo(name = "updated_at")
    val updatedAt: Long
    */
)

